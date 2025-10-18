package me.chriss99.packet

import kotlin.reflect.KClass

class PacketHandler {
    var input = ""

    private val packetConsumers: HashMap<KClass<out Packet>, MutableList<(packet: Packet) -> Unit>> = HashMap()
    fun addPacketConsumer(packet: KClass<out Packet>, consumer: (packet: Packet) -> Unit) {
        packetConsumers.getOrPut(packet) { emptyList<(packet: Packet) -> Unit>().toMutableList() }
            .add(consumer)
    }
    fun removePacketConsumer(packet: KClass<out Packet>, consumer: (packet: Packet) -> Unit) =
        packetConsumers.getOrPut(packet) { emptyList<(packet: Packet) -> Unit>().toMutableList() }
            .remove(consumer)

    fun takeInputLine(line: String) {
        if (line != "\n") {
            input += line
            return
        }

        val packet =
            try {
                Packet.parse(input)
            } catch (e: RuntimeException) {
                System.err.println("Failed to parse packet!")
                System.err.println(input)
                e.printStackTrace()
                null
            }
        input = ""

        if (packet == null)
            return

        (packetConsumers[packet::class]?: emptyList()).forEach { it(packet) }
    }

    fun sendPacket(packet: Packet) = println(packet.serialize() + "\n")
}
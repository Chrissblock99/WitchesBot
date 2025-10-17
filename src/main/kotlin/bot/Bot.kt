package me.chriss99.bot

import me.chriss99.packet.Packet
import me.chriss99.packet.PacketHandler
import me.chriss99.packet.Quit
import me.chriss99.packet.Register
import me.chriss99.shouldRun

open class Bot(val packetHandler: PacketHandler, val name: String) {
    init {
        packetHandler.sendPacket(Register(name))
        packetHandler.addPacketConsumer(Quit::class, ::quit as (packet: Packet) -> Unit)
    }

    fun quit(packet: Quit) {
        System.err.println(packet)
        shouldRun = false
    }
}
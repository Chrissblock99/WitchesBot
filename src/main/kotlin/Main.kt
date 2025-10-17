package me.chriss99

import me.chriss99.packet.PacketHandler

var shouldRun = true

fun main() {
    val packetHandler = PacketHandler()

    while (shouldRun) {
        packetHandler.takeInputLine(readln() + "\n")
    }
}
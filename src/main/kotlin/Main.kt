package me.chriss99

import me.chriss99.bot.RandomBot
import me.chriss99.packet.PacketHandler

var shouldRun = true

fun main() {
    val packetHandler = PacketHandler()
    val bot = RandomBot(packetHandler, "Chriss99")

    while (shouldRun) {
        packetHandler.takeInputLine(readln() + "\n")
    }
}
package me.chriss99

import me.chriss99.bot.RandomBot
import me.chriss99.packet.PacketHandler

var shouldRun = true

fun main(args: Array<String>) {
    val packetHandler = PacketHandler()
    val name = if (args.isEmpty()) "Chriss99" else args[0]
    val bot = RandomBot(packetHandler, name)

    while (shouldRun) {
        packetHandler.takeInputLine(readln() + "\n")
    }
}
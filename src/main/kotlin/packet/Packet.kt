package me.chriss99.packet

import kotlin.text.split

sealed class Packet {
    abstract fun serialize(): String

    companion object {
        const val VERSION = "1.0"

        val packetMap = mapOf<String, (map: Map<String, String>) -> Packet>(
            "REGISTER" to ::Register,
            "PASS_CARDS" to ::PassCards,
            "PLAY_CARD" to ::PlayCard,
            "INIT_GAME" to ::InitGame,
            "INIT_TRICK" to ::InitTrick,
            "SET_CARDS" to ::SetCards,
            "CHOOSE_CARDS" to ::ChooseCards,
            "YOUR_TURN" to ::YourTurn,
            "CARD_PLAYED" to ::CardPlayed
        )

        fun parse(packet: String): Packet {
            val lines = packet.split("\n").filter { it != "" }

            val head = lines[0]
            val splitHead = head.split(" ")
            val version = splitHead[1]
            if (version != VERSION)
                throw IllegalArgumentException("Wrong version! Expected $VERSION, found $version")
            val packetName = splitHead[0]

            val body = lines.filter { it != head }.map { it.filter { it != ' ' } }
            val map = body.map { it.split(":") }.associate { it[0] to it[1] }

            return packetMap[packetName]!!(map)
        }

        fun <T> formatList(list: List<T>, toString: (entry: T) -> String = Object::toString as (entry: T) -> String) =
            list.fold("") { acc, s -> "$acc,${toString(s)}" }.removeRange(0..0)

        fun <T> parseList(list: String, parse: (string: String) -> T): List<T> =
            list.split(",").map(parse)
    }
}
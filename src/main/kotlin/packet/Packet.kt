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
            "CARD_PLAYED" to ::CardPlayed,
            "END_ROUND" to ::EndRound,
            "QUIT" to ::Quit
        )

        fun parse(packet: String): Packet {
            val lines = packet.split("\n").filter { it != "" }

            val head = lines[0]
            val splitHead = head.split(" ")
            val version = splitHead[1]
            if (version != VERSION)
                throw IllegalArgumentException("Wrong version! Expected $VERSION, found $version")
            val packetName = splitHead[0]

            val body = lines.filter { it != head }
            val map = body.map { it.split(":") }.associate { it[0].trim().replace("[]", "") to it[1].trim() }

            return packetMap[packetName]!!(map)
        }

        fun <T> formatList(list: Collection<T>, toString: (entry: T) -> String = Object::toString as (entry: T) -> String) =
            list.fold("") { acc, s -> "$acc,${toString(s)}" }.removeRange(0..0)

        fun <T> parseList(list: String, parse: (string: String) -> T): List<T> =
            list.split(",").map(String::trim).map(parse)
    }
}
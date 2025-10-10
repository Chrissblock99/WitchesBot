package me.chriss99.packet

import me.chriss99.lib.Card

data class PlayCard(val card: Card) : Packet() {
    constructor(map: Map<String, String>) : this(Card.valueOf(map["card"]!!))

    override fun serialize(): String =
        """PLAY_CARD $VERSION
          |card: ${card.name}
        """.trimMargin()
}
package me.chriss99.packet

import me.chriss99.lib.Card

data class CardPlayed(val player: String, val card: Card) : Packet() {
    constructor(map: Map<String, String>) : this(map["player"]!!, Card.valueOf(map["card"]!!))

    override fun serialize(): String =
        """CARD_PLAYED $VERSION
          |player: $player
          |card: ${card.name}
        """.trimMargin()
}
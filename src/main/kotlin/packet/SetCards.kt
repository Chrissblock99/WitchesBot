package me.chriss99.packet

import me.chriss99.lib.CardSet

data class SetCards(val player: String, val cards: CardSet) : Packet() {
    constructor(map: Map<String, String>) : this(map["player"]!!, CardSet(map["cards"]!!))

    override fun serialize(): String =
        """SET_CARDS $VERSION
          |player: $player
          |cards[]: ${formatList(cards, { card -> card.name })}
        """.trimMargin()
}
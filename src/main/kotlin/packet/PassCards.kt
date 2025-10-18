package me.chriss99.packet

import me.chriss99.lib.Card
import me.chriss99.lib.CardSet

data class PassCards(val cards: CardSet) : Packet() {
    constructor(map: Map<String, String>) : this(CardSet(parseList(map["cards"]!!, Card::valueOf)))

    override fun serialize(): String =
        """PASS_CARDS $VERSION
          |cards[]: ${formatList(cards, { card -> card.name })}
        """.trimMargin()
}
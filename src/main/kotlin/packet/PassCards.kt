package me.chriss99.packet

import me.chriss99.lib.CardSet

data class PassCards(val cards: CardSet) : Packet() {
    constructor(map: Map<String, String>) : this(CardSet(map["cards"]!!))

    override fun serialize(): String =
        """PASS_CARDS $VERSION
          |cards: ${formatList(cards.list, { card -> card.name })}}
        """.trimMargin()
}
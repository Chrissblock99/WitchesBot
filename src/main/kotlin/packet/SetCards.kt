package me.chriss99.packet

import me.chriss99.lib.Card
import me.chriss99.lib.CardSet

data class SetCards(val player: Int, val cards: CardSet) : Packet() {
    constructor(map: Map<String, String>) : this(map["player"]!!.toInt(), CardSet(parseList(map["cards"]!!, Card::valueOf)))

    override fun serialize(): String =
        """SET_CARDS $VERSION
          |player: $player
          |cards[]: ${formatList(cards, { card -> card.name })}
        """.trimMargin()
}
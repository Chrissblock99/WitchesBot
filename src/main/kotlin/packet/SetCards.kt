package me.chriss99.packet

import me.chriss99.lib.CardSet

data class SetCards(val player: String, val cards: CardSet) : Packet() {
    constructor(map: Map<String, String>) : this(map["name"]!!, CardSet(map["cards"]!!))

    override fun serialize(): String =
        """REGISTER $VERSION
          |player: $player
          |cards: ${formatList(cards.list, { card -> card.name })})}
        """.trimMargin()
}
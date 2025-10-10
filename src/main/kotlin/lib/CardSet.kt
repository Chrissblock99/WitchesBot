package me.chriss99.lib

import me.chriss99.packet.Packet

class CardSet {
    val cards: Long

    val list get() = Card.entries.filter { it in this }

    constructor(cards: String) : this(Packet.parseList(cards, Card::valueOf))
    constructor(cards: List<Card>) {
        this.cards = cards.fold(0) { acc, card -> acc or card.indexBit }
    }

    operator fun contains(card: Card): Boolean = (cards and card.indexBit) != 0L
}
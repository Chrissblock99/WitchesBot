package me.chriss99.lib

import me.chriss99.packet.Packet

class CardSet {
    val cards: Long

    val list get() = Card.entries.filter { it in this }

    private constructor(cards: Long) {
        this.cards = cards
    }
    constructor(cards: List<Card>) : this(cards.fold(0) { acc, card -> acc or card.indexBit })
    constructor(cards: String) : this(Packet.parseList(cards, Card::valueOf))

    operator fun contains(card: Card): Boolean = (cards and card.indexBit) != 0L
    operator fun contains(color: Card.Color): Boolean = list.any { it.color == color }

    infix fun union(other: CardSet) = CardSet(this.cards or other.cards)

    fun filter(predicate: (card: Card) -> Boolean) = CardSet(list.filter(predicate))

    override fun toString() = "CardSet(cards=${cards.toULong().toString(2)})"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CardSet) return false

        if (cards != other.cards) return false

        return true
    }

    override fun hashCode(): Int {
        return cards.hashCode()
    }

    companion object {
        val ALL_CARDS = CardSet(Card.entries.toList())
        val NO_CARDS = CardSet(emptyList())
    }
}
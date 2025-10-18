package me.chriss99.lib

class CardSet(val cards: Long) : Set<Card> {
    override fun iterator() = Card.entries.filter { it in this }.iterator()
    override val size get() = cards.countOneBits()

    constructor(cards: Collection<Card>) : this(cards.fold(0) { acc, card -> acc or card.indexBit })

    override fun isEmpty() = cards == 0L

    override operator fun contains(element: Card): Boolean = (cards and element.indexBit) != 0L
    operator fun contains(color: Card.Color): Boolean = any { it.color == color }
    override fun containsAll(elements: Collection<Card>): Boolean {
        val elements = CardSet(elements).cards
        return elements and cards == elements
    }

    infix fun union(other: CardSet) = CardSet(this.cards or other.cards)
    infix fun intersect(other: CardSet) = CardSet(this.cards and other.cards)

    fun filter(predicate: (card: Card) -> Boolean) = CardSet(asSequence().filter(predicate).toList())

    override fun toString() = "CardSet(cards=${cards.toULong().toString(2)})"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CardSet) return false

        return cards == other.cards
    }

    override fun hashCode() = cards.hashCode()

    companion object {
        val ALL_CARDS = CardSet(Card.entries.toList())
        val NO_CARDS = CardSet(0)
    }
}
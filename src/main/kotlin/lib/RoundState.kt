package me.chriss99.lib

import me.chriss99.lib.Card.Color

data class RoundState(val players: List<Player>, val cards: List<Card>, val actingPlayer: Player) {
    init {
        val gameCards = players.fold(CardSet(cards)) { acc, player -> acc union player.cards union player.tricks }
        if (gameCards != CardSet.ALL_CARDS)
            throw IllegalStateException("GameState must contain all cards! But only has $gameCards")
    }

    val trumpf: Color? = cards.firstNotNullOfOrNull { card -> if (card.color == Color.FOOL) null else card.color }
    val highestCard: Card? = cards.filter { it.color == trumpf }.maxByOrNull(Card::number)
    val playableCards = if (trumpf == null || trumpf !in actingPlayer.cards) actingPlayer.cards else
        actingPlayer.cards.filter { it.color in listOf(trumpf, Color.FOOL) }

    fun playCard(card: Card): RoundState {
        if (card !in playableCards)
            throw IllegalArgumentException("Card is not playable! $card !in $playableCards")

        val actingPlayer = this.actingPlayer.copy(cards = actingPlayer.cards.filter { it != card })
        val nextPlayer = players[actingPlayer.index + 1 % players.size]
        val cards = listOf(*this.cards.toTypedArray(), card)

        val players = players.map { if (it == this.actingPlayer) actingPlayer else it }
        if (cards.size != players.size)
            return RoundState(players, cards, nextPlayer)


        val winner = players[cards.indexOf(highestCard) + nextPlayer.index]
        val updatedWinner = winner.copy(tricks = winner.tricks union CardSet(cards))

        return RoundState(players.map { if (it == winner) updatedWinner else it }, emptyList(), winner)
    }

    companion object {
        fun randomInit(players: List<String>, beginnerIndex: Int): RoundState {
            val cards = CardSet.ALL_CARDS.list.shuffled()
            val cardsPerPlayer = 60 / players.size

            val players = players.mapIndexed { index, name -> Player(index, name,
                CardSet(cards.subList(index*cardsPerPlayer, index*cardsPerPlayer + cardsPerPlayer)),
                CardSet.NO_CARDS, 0) }
            return RoundState(players, emptyList(), players[beginnerIndex])
        }
    }
}
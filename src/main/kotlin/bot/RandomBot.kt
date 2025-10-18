package me.chriss99.bot

import me.chriss99.lib.CardSet
import me.chriss99.packet.CardPlayed
import me.chriss99.packet.ChooseCards
import me.chriss99.packet.InitGame
import me.chriss99.packet.InitTrick
import me.chriss99.packet.PacketHandler
import me.chriss99.packet.PassCards
import me.chriss99.packet.PlayCard
import me.chriss99.packet.SetCards
import me.chriss99.packet.YourTurn

class RandomBot(packetHandler: PacketHandler, username: String) : Bot(packetHandler, username) {
    var id = -1
    lateinit var cards: CardSet

    override fun initGame(packet: InitGame) {
        id = packet.players.indexOf(username)
    }

    override fun setCards(packet: SetCards) {
        if (packet.player == id)
            cards = packet.cards
    }

    override fun chooseCards(packet: ChooseCards) {
        packetHandler.sendPacket(PassCards(CardSet(cards.shuffled().subList(0, packet.amount))))
    }

    override fun yourTurn(packet: YourTurn) {
        packetHandler.sendPacket(PlayCard(packet.validMoves.random()))
    }

    override fun initTrick(packet: InitTrick) {}
    override fun cardPlayed(packet: CardPlayed) {}
}
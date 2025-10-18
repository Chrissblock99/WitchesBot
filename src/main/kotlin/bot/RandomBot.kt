package me.chriss99.bot

import me.chriss99.lib.CardSet
import me.chriss99.packet.ChooseCards
import me.chriss99.packet.Packet
import me.chriss99.packet.PacketHandler
import me.chriss99.packet.PassCards
import me.chriss99.packet.PlayCard
import me.chriss99.packet.SetCards
import me.chriss99.packet.YourTurn

class RandomBot(packetHandler: PacketHandler, name: String) : Bot(packetHandler, name) {
    init {
        packetHandler.addPacketConsumer(SetCards::class, ::setCards as (packet: Packet) -> Unit)
        packetHandler.addPacketConsumer(ChooseCards::class, ::chooseCards as (packet: Packet) -> Unit)
        packetHandler.addPacketConsumer(YourTurn::class, ::yourTurn as (packet: Packet) -> Unit)
    }

    lateinit var cards: CardSet

    fun setCards(packet: SetCards) {
        if (packet.player == name)
            cards = packet.cards
    }

    fun chooseCards(packet: ChooseCards) {
        packetHandler.sendPacket(PassCards(CardSet(cards.shuffled().subList(0, packet.amount))))
    }

    fun yourTurn(packet: YourTurn) {
        packetHandler.sendPacket(PlayCard(packet.validMoves.random()))
    }
}
package me.chriss99.bot

import me.chriss99.packet.CardPlayed
import me.chriss99.packet.ChooseCards
import me.chriss99.packet.InitGame
import me.chriss99.packet.InitTrick
import me.chriss99.packet.Packet
import me.chriss99.packet.PacketHandler
import me.chriss99.packet.Quit
import me.chriss99.packet.Register
import me.chriss99.packet.SetCards
import me.chriss99.packet.YourTurn
import me.chriss99.shouldRun

abstract class Bot(val packetHandler: PacketHandler, val username: String) {
    init {
        packetHandler.sendPacket(Register(username))
        packetHandler.addPacketConsumer(InitGame::class, ::initGame as (packet: Packet) -> Unit)
        packetHandler.addPacketConsumer(SetCards::class, ::setCards as (packet: Packet) -> Unit)
        packetHandler.addPacketConsumer(ChooseCards::class, ::chooseCards as (packet: Packet) -> Unit)
        packetHandler.addPacketConsumer(InitTrick::class, ::initTrick as (packet: Packet) -> Unit)
        packetHandler.addPacketConsumer(YourTurn::class, ::yourTurn as (packet: Packet) -> Unit)
        packetHandler.addPacketConsumer(CardPlayed::class, ::cardPlayed as (packet: Packet) -> Unit)
        packetHandler.addPacketConsumer(Quit::class, ::quit as (packet: Packet) -> Unit)
    }

    abstract fun initGame(packet: InitGame)
    abstract fun setCards(packet: SetCards)
    abstract fun chooseCards(packet: ChooseCards)
    abstract fun initTrick(packet: InitTrick)
    abstract fun yourTurn(packet: YourTurn)
    abstract fun cardPlayed(packet: CardPlayed)

    fun quit(packet: Quit) {
        System.err.println(packet)
        shouldRun = false
    }
}
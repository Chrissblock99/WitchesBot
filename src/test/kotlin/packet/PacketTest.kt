package packet

import me.chriss99.packet.Packet
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PacketTest {
    @Test
    fun register() {
        val packet = """REGISTER 1.0
          |username: player1
        """.trimMargin()
        assertEquals(packet, Packet.parse(packet).serialize())
    }

    @Test
    fun passCards() {
        val packet = """PASS_CARDS 1.0
          |cards[]: F1,BE,Y7
        """.trimMargin()
        assertEquals(packet, Packet.parse(packet).serialize())
    }

    @Test
    fun playCard() {
        val packet = """PLAY_CARD 1.0
          |card: R3
        """.trimMargin()
        assertEquals(packet, Packet.parse(packet).serialize())
    }

    @Test
    fun initGame() {
        val packet = """INIT_GAME 1.0
          |players[]: player1,player2,player3,player4
          |scores[]: 0,0,0,0
          |maxScore: 60
          |totalTime: 235647
          |timeIncrement: 24
        """.trimMargin()
        assertEquals(packet, Packet.parse(packet).serialize())
    }

    @Test
    fun initTrick() {
        val packet = """INIT_TRICK 1.0
          |beginner: 1
        """.trimMargin()
        assertEquals(packet, Packet.parse(packet).serialize())
    }

    @Test
    fun setCards() {
        val packet = """SET_CARDS 1.0
          |player: 2
          |cards[]: F4,R1,GA
        """.trimMargin()
        assertEquals(packet, Packet.parse(packet).serialize())
    }

    @Test
    fun chooseCards() {
        val packet = """CHOOSE_CARDS 1.0
          |timeLeft: 73242
          |passingOrder[]: 2,3,0,1
          |amount: 3
        """.trimMargin()
        assertEquals(packet, Packet.parse(packet).serialize())
    }

    @Test
    fun yourTurn() {
        val packet = """YOUR_TURN 1.0
          |timeLeft: 234452
          |validMoves[]: F3,R1,R4,GA,B3,Y3
        """.trimMargin()
        assertEquals(packet, Packet.parse(packet).serialize())
    }

    @Test
    fun cardPlayed() {
        val packet = """CARD_PLAYED 1.0
          |player: 3
          |card: F2
        """.trimMargin()
        assertEquals(packet, Packet.parse(packet).serialize())
    }

    @Test
    fun endRound() {
        val packet = """END_ROUND 1.0
          |scores[]: 1,2,2,3
        """.trimMargin()
        assertEquals(packet, Packet.parse(packet).serialize())
    }

    @Test
    fun quit() {
        val packet = """QUIT 1.0
          |reason: Game ended
          |error: false
        """.trimMargin()
        assertEquals(packet, Packet.parse(packet).serialize())
    }
}
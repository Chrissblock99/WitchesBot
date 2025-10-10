package me.chriss99.packet

import me.chriss99.lib.CardSet

data class YourTurn(val timeLeft: Int, val validMoves: CardSet) : Packet() {
    constructor(map: Map<String, String>) : this(map["timeLeft"]!!.toInt(), CardSet(map["validMoves"]!!))

    override fun serialize(): String =
        """REGISTER $VERSION
          |timeLeft: $timeLeft
          |validMoves: ${formatList(validMoves.list, { card -> card.name })}
        """.trimMargin()
}
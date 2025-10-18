package me.chriss99.packet

data class EndRound(val scores: List<Int>) : Packet() {
    constructor(map: Map<String, String>) : this(parseList(map["scores"]!!, String::toInt))

    override fun serialize(): String =
        """END_ROUND $VERSION
          |scores[]: ${formatList(scores)}
        """.trimMargin()
}
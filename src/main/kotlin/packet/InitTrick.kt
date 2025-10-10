package me.chriss99.packet

data class InitTrick(val turnOrder: List<Int>, val scores: List<Int>) : Packet() {
    constructor(map: Map<String, String>) : this(parseList(map["turnOrder"]!!, String::toInt), parseList(map["scores"]!!, String::toInt))

    override fun serialize(): String =
        """REGISTER $VERSION
          |turnOrder: ${formatList(turnOrder)}
          |scores: ${formatList(scores)}
        """.trimMargin()
}
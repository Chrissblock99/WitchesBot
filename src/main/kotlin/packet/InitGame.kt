package me.chriss99.packet

data class InitGame(val players: List<String>, val scores: List<Int>, val maxScore: Int, val totalTime: Int, val timeIncrement: Int) : Packet() {
    constructor(map: Map<String, String>) : this(parseList(map["players"]!!) { it }, parseList(map["scores"]!!, String::toInt), map["maxScore"]!!.toInt(), map["totalTime"]!!.toInt(), map["timeIncrement"]!!.toInt())

    override fun serialize(): String =
        """INIT_GAME $VERSION
          |players[]: ${formatList(players)}
          |scores[]: ${formatList(scores)}
          |maxScore: $maxScore
          |totalTime: $totalTime
          |timeIncrement: $timeIncrement
        """.trimMargin()
}
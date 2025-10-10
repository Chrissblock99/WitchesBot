package me.chriss99.packet

data class InitGame(val players: List<String>, val totalTime: Int, val timeIncrement: Int) : Packet() {
    constructor(map: Map<String, String>) : this(map["players"]!!.split(","), map["totalTime"]!!.toInt(), map["timeIncrement"]!!.toInt())

    override fun serialize(): String =
        """INIT_GAME $VERSION
          |players: ${formatList(players)}
          |totalTime: $totalTime
          |timeIncrement: $timeIncrement
        """.trimMargin()
}
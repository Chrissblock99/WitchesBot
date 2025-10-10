package me.chriss99.packet

data class InitTrick(val beginner: Int, val scores: List<Int>) : Packet() {
    constructor(map: Map<String, String>) : this(map["beginner"]!!.toInt(), parseList(map["scores"]!!, String::toInt))

    override fun serialize(): String =
        """INIT_TRICK $VERSION
          |beginner: $beginner
          |scores: ${formatList(scores)}
        """.trimMargin()
}
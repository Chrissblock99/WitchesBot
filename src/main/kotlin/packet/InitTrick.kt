package me.chriss99.packet

data class InitTrick(val beginner: Int) : Packet() {
    constructor(map: Map<String, String>) : this(map["beginner"]!!.toInt())

    override fun serialize(): String =
        """INIT_TRICK $VERSION
          |beginner: $beginner
        """.trimMargin()
}
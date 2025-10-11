package me.chriss99.packet

data class Quit(val reason: String, val error: Boolean) : Packet() {
    constructor(map: Map<String, String>) : this(map["reason"]!!, map["error"]!!.toBoolean())

    override fun serialize(): String =
        """QUIT $VERSION
          |reason: $reason
          |error: $error
        """.trimMargin()
}
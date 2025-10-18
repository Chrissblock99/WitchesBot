package me.chriss99.packet

data class Register(val name: String) : Packet() {
    constructor(map: Map<String, String>) : this(map["username"]!!)

    override fun serialize(): String =
        """REGISTER $VERSION
          |username: $name
        """.trimMargin()
}
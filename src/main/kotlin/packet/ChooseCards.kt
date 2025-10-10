package me.chriss99.packet

data class ChooseCards(val timeLeft: Int, val passingOrder: List<Int>, val amount: Int) : Packet() {
    constructor(map: Map<String, String>) : this(map["timeLeft"]!!.toInt(), parseList(map["passingOrder"]!!, String::toInt), map["amount"]!!.toInt())

    override fun serialize(): String =
        """REGISTER $VERSION
          |timeLeft: $timeLeft
          |passingOrder: ${formatList(passingOrder)}
          |amount: $amount
        """.trimMargin()
}
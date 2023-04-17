package model

import java.io.Serializable
import java.time.LocalDateTime

data class TicketModel(
    val title: String,
    val reserveTime: LocalDateTime,
    val price: Int,
    val peopleNumber: Int,
) : Serializable {
    fun getTotalPrice(): Int = peopleNumber * price

    companion object {
        val EMPTY = TicketModel("", LocalDateTime.MIN, 0, 0)
    }
}

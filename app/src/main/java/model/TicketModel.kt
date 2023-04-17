package model

import java.io.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class TicketModel(
    val title: String,
    val reserveTime: LocalDateTime,
    val price: Int,
    val peopleNumber: Int,
) : Serializable {
    fun getTotalPrice(): Int = peopleNumber * price

    fun getReserveDate(): String {
        return reserveTime.format(DATE_FORMATTER)
    }

    companion object {
        private val DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        val EMPTY = TicketModel("", LocalDateTime.MIN, 0, 0)
    }
}

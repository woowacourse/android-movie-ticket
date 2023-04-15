package entity

import java.io.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class MovieTicket(
    val title: String,
    val reserveTime: LocalDateTime,
    val people: List<MovieTicketPerson>,
) : Serializable {
    fun getTotalPrice(): Int = people.sumOf { it.price }

    fun getReserveDate(): String {
        return reserveTime.format(DATE_FORMATTER)
    }

    companion object {
        private val DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    }
}

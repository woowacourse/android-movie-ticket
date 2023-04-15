package entity

import java.io.Serializable
import java.time.LocalDateTime

data class MovieTicket(
    val title: String,
    val reserveTime: LocalDateTime,
    val people: List<MovieTicketPerson>,
) : Serializable {
    fun getTotalPrice(): Int = people.sumOf { it.price }
}

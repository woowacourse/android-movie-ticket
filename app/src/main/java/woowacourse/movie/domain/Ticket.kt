package woowacourse.movie.domain

import java.io.Serializable
import java.time.LocalDateTime

data class Ticket(
    val price: Int,
    val date: LocalDateTime,
    val movieTitle: String,
    val numberOfPeople: Int,
) : Serializable {
    fun calculateTicketPrice(): Int = price * numberOfPeople
}

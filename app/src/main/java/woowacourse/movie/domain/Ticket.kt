package woowacourse.movie.domain

import java.time.LocalDate
import java.time.LocalTime

data class Ticket(
    val movieTitle: String,
    val date: LocalDate,
    val time: LocalTime,
    val quantity: TicketQuantity,
)

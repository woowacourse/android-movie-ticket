package woowacourse.movie.domain

import java.time.LocalDate
import java.time.LocalTime

data class TicketInfo(
    val movieTitle: String,
    val date: LocalDate,
    val time: LocalTime,
    val quantity: TicketQuantity,
)

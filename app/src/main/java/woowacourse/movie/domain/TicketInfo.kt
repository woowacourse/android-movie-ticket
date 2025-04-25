package woowacourse.movie.domain

import java.time.LocalDate
import java.time.LocalTime

data class TicketInfo(
    val movie: Movie,
    val date: LocalDate,
    val time: LocalTime,
    val quantity: TicketQuantity,
)

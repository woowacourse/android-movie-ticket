package woowacourse.movie.model

import java.time.LocalDate
import java.time.LocalTime

data class Ticket(
    val title: String,
    val date: LocalDate,
    val time: LocalTime,
    val seats: List<Seat>,
    val totalPrice: Int,
)

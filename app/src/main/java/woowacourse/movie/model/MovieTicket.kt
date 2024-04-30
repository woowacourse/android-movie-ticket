package woowacourse.movie.model

import java.time.LocalDate
import java.time.LocalTime

data class MovieTicket(
    val title: String,
    val date: LocalDate,
    val time: LocalTime,
    val count: Int,
    val seats: MovieSelectedSeats,
)

package woowacourse.movie.model

import java.time.LocalDate
import java.time.LocalTime

class MovieTicket(
    val title: String,
    val date: LocalDate,
    val time: LocalTime,
    val count: Int,
    val seats: List<MovieSeat>,
) {
    val totalPrice =
        seats.sumOf { seat ->
            seat.grade.price
        }
}

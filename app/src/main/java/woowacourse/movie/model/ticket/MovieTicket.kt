package woowacourse.movie.model.ticket

import woowacourse.movie.model.movie.MovieTime
import woowacourse.movie.model.seat.Seat
import java.io.Serializable
import java.time.LocalDate

data class MovieTicket(
    val title: String,
    val movieDate: LocalDate,
    val movieTime: MovieTime,
    val seats: List<Seat>,
) : Serializable {
    fun price(): Int = seats.sumOf { seat -> seat.price }
}

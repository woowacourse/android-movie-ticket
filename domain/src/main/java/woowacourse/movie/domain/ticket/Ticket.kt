package woowacourse.movie.domain.ticket

import woowacourse.movie.domain.price.Price
import woowacourse.movie.domain.seat.Seat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class Ticket private constructor(
    val title: String,
    val playingDateTime: LocalDateTime,
    val seats: List<Seat>,
    val price: Price,
) {
    companion object {
        fun of(
            title: String,
            playingDate: LocalDate,
            playingTime: LocalTime,
            count: Int,
            seats: List<Seat>,
            price: Price,
        ): Ticket {
            val mergeDate = LocalDateTime.of(playingDate, playingTime)
            return Ticket(title, mergeDate, seats, price)
        }
    }
}

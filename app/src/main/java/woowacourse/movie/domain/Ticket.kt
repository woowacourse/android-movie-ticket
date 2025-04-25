package woowacourse.movie.domain

import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.domain.seat.Seats
import java.time.LocalDateTime
import java.time.LocalTime

data class Ticket(
    val movie: Movie,
    val showtime: LocalDateTime = LocalDateTime.of(movie.startDate, LocalTime.MIDNIGHT),
    val count: Int = MINIMUM_TICKET_COUNT,
    val seats: Seats = Seats(),
) {
    init {
        require(count >= MINIMUM_TICKET_COUNT)
    }

    fun isMinimumCount(): Boolean = count == MINIMUM_TICKET_COUNT

    fun isSeatsAllSelected(): Boolean = count == seats.size()

    fun totalPrice(): Int = seats.totalPrice()

    fun contains(seat: Seat): Boolean = seats.contains(seat)

    companion object {
        private const val MINIMUM_TICKET_COUNT = 1
    }
}

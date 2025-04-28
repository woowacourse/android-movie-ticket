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
        require(count >= MINIMUM_TICKET_COUNT) { ERROR_INVALID_TICKET_COUNT }
    }

    fun isMinimumCount(): Boolean = count == MINIMUM_TICKET_COUNT

    fun totalPrice(): Int = seats.totalPrice()

    fun contains(seat: Seat): Boolean = seats.contains(seat)

    companion object {
        private const val MINIMUM_TICKET_COUNT = 1
        private const val ERROR_INVALID_TICKET_COUNT = "티켓은 최소 1개여야 합니다."
    }
}

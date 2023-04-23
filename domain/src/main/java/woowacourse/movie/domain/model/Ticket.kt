package woowacourse.movie.domain.model

import woowacourse.movie.domain.tools.Money
import woowacourse.movie.domain.tools.seat.Seat
import woowacourse.movie.domain.tools.seat.Seats
import java.time.LocalDateTime
import java.util.SortedSet

data class Ticket(
    val movieId: Long,
    val bookedDateTime: LocalDateTime,
    val count: Int,
    val seats: SortedSet<Seat>,
) {
    fun getPaymentMoney(): Money = Seats(seats).getPaymentMoney(bookedDateTime)
}

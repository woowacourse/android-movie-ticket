package woowacourse.movie.domain.model.tools

import woowacourse.movie.domain.model.rules.SeatsPayment
import woowacourse.movie.domain.model.tools.seat.Seats
import java.time.LocalDateTime

data class Ticket(
    val movieId: Long,
    val bookedDateTime: LocalDateTime,
    val count: Int,
    val seats: Seats,
) {
    fun getPaymentMoney(): Money = SeatsPayment(seats).getDiscountedMoneyByDateTime(bookedDateTime)
}

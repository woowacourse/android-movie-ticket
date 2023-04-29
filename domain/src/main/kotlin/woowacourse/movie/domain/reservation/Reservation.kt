package woowacourse.movie.domain.reservation

import woowacourse.movie.domain.movie.Movie
import woowacourse.movie.domain.payment.PaymentAmount
import woowacourse.movie.domain.payment.PaymentType
import woowacourse.movie.domain.seat.Seat
import java.time.LocalDateTime

data class Reservation(
    val movie: Movie,
    val screeningDateTime: LocalDateTime,
    val ticketCount: Int,
    val seats: List<Seat>,
    val paymentAmount: PaymentAmount,
    val paymentType: PaymentType = PaymentType.LOCAL_PAYMENT
)

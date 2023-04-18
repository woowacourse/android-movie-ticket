package woowacourse.movie.domain.payment

import woowacourse.movie.domain.discount.Discount
import woowacourse.movie.domain.discount.DiscountRule
import woowacourse.movie.domain.discount.EarlyNightDiscount
import woowacourse.movie.domain.discount.MovieDayDiscount
import woowacourse.movie.domain.movie.Movie
import java.io.Serializable
import java.time.LocalDateTime

data class Reservation(
    val movie: Movie,
    val screeningDateTime: LocalDateTime,
    val ticketCount: Int,
    val paymentAmount: PaymentAmount,
    val paymentType: PaymentType = PaymentType.LOCAL_PAYMENT
) : Serializable {

    companion object {

        private const val TICKET_PRICE = 13_000

        fun from(movie: Movie, ticketCount: Int, screeningDateTime: LocalDateTime): Reservation {
            val discount: DiscountRule = Discount(EarlyNightDiscount(), MovieDayDiscount())
            val paymentAmount: PaymentAmount = discount.getPaymentAmountResult(
                PaymentAmount(ticketCount * TICKET_PRICE),
                screeningDateTime
            )
            return Reservation(
                movie = movie,
                screeningDateTime = screeningDateTime,
                ticketCount = ticketCount,
                paymentAmount = paymentAmount,
            )
        }
    }
}

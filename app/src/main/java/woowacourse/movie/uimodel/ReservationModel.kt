package woowacourse.movie.uimodel

import woowacourse.movie.domain.discount.Discount
import woowacourse.movie.domain.discount.DiscountRule
import woowacourse.movie.domain.discount.EarlyNightDiscount
import woowacourse.movie.domain.discount.MovieDayDiscount
import woowacourse.movie.domain.payment.PaymentAmount
import woowacourse.movie.domain.payment.PaymentType
import java.io.Serializable
import java.time.LocalDateTime

data class ReservationModel(
    val movieModel: MovieModel,
    val screeningDateTime: LocalDateTime,
    val ticketCount: Int,
    val paymentAmount: PaymentAmount,
    val paymentType: PaymentType = PaymentType.LOCAL_PAYMENT
) : Serializable {

    companion object {
        private const val TICKET_PRICE = 13_000

        fun from(movieModel: MovieModel, ticketCount: Int, screeningDateTime: LocalDateTime): ReservationModel {
            val discount: DiscountRule = Discount(EarlyNightDiscount(), MovieDayDiscount())
            val paymentAmount: PaymentAmount = discount.getPaymentAmountResult(
                PaymentAmount(ticketCount * TICKET_PRICE),
                screeningDateTime
            )
            return ReservationModel(
                movieModel = movieModel,
                screeningDateTime = screeningDateTime,
                ticketCount = ticketCount,
                paymentAmount = paymentAmount,
            )
        }
    }
}

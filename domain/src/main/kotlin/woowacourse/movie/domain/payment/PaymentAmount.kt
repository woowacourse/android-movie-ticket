package woowacourse.movie.domain.payment

import woowacourse.movie.domain.discount.Discount
import woowacourse.movie.domain.discount.EarlyNightDiscount
import woowacourse.movie.domain.discount.MovieDayDiscount
import woowacourse.movie.domain.seat.Seat
import java.time.LocalDateTime

@JvmInline
value class PaymentAmount(val value: Int = 0) {
    init {
        require(value >= MINIMUM)
    }

    override fun toString(): String {
        return value.toString()
    }

    companion object {
        const val MINIMUM = 0

        fun applyDiscount(seats: List<Seat>, screeningDateTime: LocalDateTime): PaymentAmount {
            val discount: Discount = Discount(MovieDayDiscount(), EarlyNightDiscount())
            return PaymentAmount(
                seats.sumOf {
                    discount.getPaymentAmountResult(
                        PaymentAmount(it.seatType.paymentAmount),
                        screeningDateTime
                    ).value
                }
            )
        }
    }
}

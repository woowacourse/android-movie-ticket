package payment

import discount.Discount
import discount.EarlyNightDiscount
import discount.MovieDayDiscount
import seat.Seat
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

        fun from(seats: List<Seat>, screeningDateTime: LocalDateTime): PaymentAmount {
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

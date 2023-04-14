package domain.discount

import domain.payment.PaymentAmount
import java.time.LocalDateTime

class MovieDayDiscountEvent : DiscountEvent {

    override fun discount(paymentAmount: PaymentAmount, screeningDateTime: LocalDateTime): PaymentAmount {
        if (!canApplyDiscount(screeningDateTime)) {
            return paymentAmount
        }
        return PaymentAmount((paymentAmount.value * DISCOUNT_PERCENTAGE).toInt())
    }

    private fun canApplyDiscount(screeningDateTime: LocalDateTime): Boolean {
        if (screeningDateTime.dayOfMonth in MOVIE_DAYS) {
            return true
        }
        return false
    }

    companion object {
        private const val DISCOUNT_PERCENTAGE = 0.9
        private val MOVIE_DAYS = listOf(10, 20, 30)
    }
}

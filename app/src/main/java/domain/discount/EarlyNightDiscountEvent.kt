package domain.discount

import domain.payment.PaymentAmount
import java.time.LocalDateTime

class EarlyNightDiscountEvent : DiscountEvent {

    override fun discount(
        paymentAmount: PaymentAmount,
        screeningDateTime: LocalDateTime
    ): PaymentAmount {
        if (!canApplyDiscount(screeningDateTime)) {
            return paymentAmount
        }
        return PaymentAmount(paymentAmount.value - DISCOUNT_AMOUNT)
    }

    private fun canApplyDiscount(screeningDateTime: LocalDateTime): Boolean {
        if (screeningDateTime.hour <= EARLY_TIME_STANDARD || screeningDateTime.hour >= NIGHT_TIME_STANDARD) {
            return true
        }
        return false
    }

    companion object {
        private const val DISCOUNT_AMOUNT = 2000
        private const val EARLY_TIME_STANDARD = 11
        private const val NIGHT_TIME_STANDARD = 20
    }
}

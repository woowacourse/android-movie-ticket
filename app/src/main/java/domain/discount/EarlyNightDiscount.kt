package domain.discount

import domain.payment.PaymentAmount
import java.time.LocalDateTime

class EarlyNightDiscount : DiscountRule {
    override fun getPaymentAmountResult(
        paymentAmount: PaymentAmount,
        screeningDateTime: LocalDateTime
    ): PaymentAmount {
        if (!isDiscountCondition(screeningDateTime)) {
            return paymentAmount
        }
        return PaymentAmount(paymentAmount.value - DISCOUNT_AMOUNT)
    }

    private fun isDiscountCondition(screeningDateTime: LocalDateTime): Boolean {
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

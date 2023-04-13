package domain.discount

import domain.payment.PaymentAmount
import java.time.LocalDateTime

class EarlyNightDiscount : DiscountRule {
    override fun getPaymentAmountResult(paymentAmount: PaymentAmount, screeningDateTime: LocalDateTime): PaymentAmount {
        if (!isDiscountCondition(screeningDateTime)) return paymentAmount
        return PaymentAmount(paymentAmount.value - 2000)
    }

    private fun isDiscountCondition(screeningDateTime: LocalDateTime): Boolean {
        if (screeningDateTime.hour <= 11) return true
        if (screeningDateTime.hour >= 20) return true
        return false
    }
}

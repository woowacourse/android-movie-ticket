package domain.discount

import domain.PaymentAmount
import java.time.LocalDateTime

class MovieDayDiscount : DiscountRule {
    override fun getPaymentAmountResult(paymentAmount: PaymentAmount, screeningDateTime: LocalDateTime): PaymentAmount {
        if (!isDiscountCondition(screeningDateTime)) return paymentAmount
        return PaymentAmount((paymentAmount.value * 0.9).toInt())
    }

    private fun isDiscountCondition(screeningDateTime: LocalDateTime): Boolean {
        if (screeningDateTime.dayOfMonth in listOf(10, 20, 30)) return true
        return false
    }
}

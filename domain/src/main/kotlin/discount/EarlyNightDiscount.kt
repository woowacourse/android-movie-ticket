package discount

import payment.PaymentAmount
import java.time.LocalDateTime

class EarlyNightDiscount(
    private val amount: Int = DEFAULT_EARLY_NIGHT_AMOUNT,
    private val earlyTime: Int = DEFAULT_EARLY_TIME,
    private val nightTime: Int = DEFAULT_NIGHT_TIME
) : DiscountRule {

    override fun getPaymentAmountResult(paymentAmount: PaymentAmount, screeningDateTime: LocalDateTime): PaymentAmount {
        if (!isDiscountCondition(screeningDateTime)) return paymentAmount
        return PaymentAmount(paymentAmount.value - amount)
    }

    private fun isDiscountCondition(screeningDateTime: LocalDateTime): Boolean {
        if (screeningDateTime.hour <= earlyTime) return true
        if (screeningDateTime.hour >= nightTime) return true
        return false
    }

    companion object {
        private const val DEFAULT_EARLY_NIGHT_AMOUNT: Int = 2_000
        private const val DEFAULT_EARLY_TIME: Int = 11
        private const val DEFAULT_NIGHT_TIME: Int = 20
    }
}

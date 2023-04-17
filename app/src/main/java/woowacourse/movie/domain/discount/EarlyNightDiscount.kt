package woowacourse.movie.domain.discount

import woowacourse.movie.domain.payment.PaymentAmount
import java.time.LocalDateTime

class EarlyNightDiscount(
    private val amount: Int,
    private val earlyTime: Int,
    private val nightTime: Int
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
}

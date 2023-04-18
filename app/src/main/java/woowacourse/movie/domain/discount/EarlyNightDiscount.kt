package woowacourse.movie.domain.discount

import woowacourse.movie.domain.payment.PaymentAmount
import java.time.LocalDateTime

class EarlyNightDiscount(
    private val amount: Int = DiscountPolicy.earlyNightAmount,
    private val earlyTime: Int = DiscountPolicy.earlyTime,
    private val nightTime: Int = DiscountPolicy.nightTime
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

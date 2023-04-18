package woowacourse.movie.domain.discount

import woowacourse.movie.domain.payment.PaymentAmount
import java.time.LocalDateTime

class MovieDayDiscount(
    private val percentage: Double = DiscountPolicy.movieDayPercentage,
    private val movieDays: List<Int> = DiscountPolicy.movieDays
) : DiscountRule {

    override fun getPaymentAmountResult(paymentAmount: PaymentAmount, screeningDateTime: LocalDateTime): PaymentAmount {
        if (!isDiscountCondition(screeningDateTime)) return paymentAmount
        return PaymentAmount((paymentAmount.value * percentage).toInt())
    }

    private fun isDiscountCondition(screeningDateTime: LocalDateTime): Boolean {
        if (screeningDateTime.dayOfMonth in movieDays) return true
        return false
    }
}

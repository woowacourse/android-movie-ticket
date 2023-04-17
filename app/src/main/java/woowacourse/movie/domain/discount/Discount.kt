package woowacourse.movie.domain.discount

import woowacourse.movie.domain.discount.DiscountPolicy.earlyNightAmount
import woowacourse.movie.domain.discount.DiscountPolicy.earlyTime
import woowacourse.movie.domain.discount.DiscountPolicy.movieDayPercentage
import woowacourse.movie.domain.discount.DiscountPolicy.movieDays
import woowacourse.movie.domain.discount.DiscountPolicy.nightTime
import woowacourse.movie.domain.payment.PaymentAmount
import java.time.LocalDateTime

class Discount : DiscountRule {
    override fun getPaymentAmountResult(
        paymentAmount: PaymentAmount,
        screeningDateTime: LocalDateTime
    ): PaymentAmount {
        val discounts: List<DiscountRule> = listOf(
            MovieDayDiscount(movieDayPercentage, movieDays),
            EarlyNightDiscount(earlyNightAmount, earlyTime, nightTime)
        )
        var resultPaymentAmount: PaymentAmount = paymentAmount

        discounts.forEach {
            resultPaymentAmount = it.getPaymentAmountResult(resultPaymentAmount, screeningDateTime)
        }

        return resultPaymentAmount
    }
}

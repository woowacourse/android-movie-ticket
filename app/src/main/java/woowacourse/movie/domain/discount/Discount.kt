package woowacourse.movie.domain.discount

import woowacourse.movie.domain.payment.PaymentAmount
import java.time.LocalDateTime

class Discount(
    private val discountRules: List<DiscountRule>
) : DiscountRule {

    constructor(vararg rules: DiscountRule) : this(rules.map { it }.toList())

    override fun getPaymentAmountResult(
        paymentAmount: PaymentAmount,
        screeningDateTime: LocalDateTime
    ): PaymentAmount {

        var resultPaymentAmount: PaymentAmount = paymentAmount
        discountRules.forEach {
            resultPaymentAmount = it.getPaymentAmountResult(resultPaymentAmount, screeningDateTime)
        }
        return resultPaymentAmount
    }
}

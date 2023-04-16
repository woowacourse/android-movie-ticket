package woowacourse.movie.domain.discount.discountpolicy

import woowacourse.movie.domain.discount.discountcondition.DiscountConditionWithTimes
import java.time.LocalDateTime

class EarlyBirdTimeDiscountPolicy(
    override val dateTime: LocalDateTime,
    discountAmount: Int,
) : DateTimeDiscountPolicy, AmountDiscountPolicy(discountAmount) {

    override fun isDiscount(): Boolean =
        DiscountConditionWithTimes(EARLY_TIMES).isDiscount(dateTime)

    companion object {
        private val EARLY_TIMES = listOf(9, 10, 11)
    }
}

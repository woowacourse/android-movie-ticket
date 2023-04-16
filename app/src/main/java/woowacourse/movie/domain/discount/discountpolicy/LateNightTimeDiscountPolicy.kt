package woowacourse.movie.domain.discount.discountpolicy

import woowacourse.movie.domain.discount.discountcondition.DiscountConditionWithTimes
import java.time.LocalDateTime

class LateNightTimeDiscountPolicy(
    override val dateTime: LocalDateTime,
    discountAmount: Int,
) : DateTimeDiscountPolicy, AmountDiscountPolicy(discountAmount) {

    override fun isDiscount(): Boolean = DiscountConditionWithTimes(LATE_TIMES).isDiscount(dateTime)

    companion object {
        private val LATE_TIMES = listOf(20, 21, 22, 23)
    }
}

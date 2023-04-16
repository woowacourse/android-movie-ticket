package woowacourse.movie.domain.discount.discountpolicy

import woowacourse.movie.domain.discount.discountcondition.DiscountConditionWithDays
import java.time.LocalDateTime

class MovieDayTimeDiscountPolicy(
    override val dateTime: LocalDateTime,
    discountRate: Double,
) : DateTimeDiscountPolicy, RateDiscountPolicy(discountRate) {

    override fun isDiscount(): Boolean = DiscountConditionWithDays(MOVIE_DAYS).isDiscount(dateTime)

    companion object {
        private val MOVIE_DAYS = listOf(10, 20, 30)
    }
}

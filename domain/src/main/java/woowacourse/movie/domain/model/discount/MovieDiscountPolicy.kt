package woowacourse.movie.domain.model.discount

import woowacourse.movie.domain.model.discount.discountcondition.DiscountConditionWithDays
import woowacourse.movie.domain.model.discount.discountcondition.DiscountConditionWithTimes
import woowacourse.movie.domain.model.discount.discountpolicy.AmountDiscountPolicy
import woowacourse.movie.domain.model.discount.discountpolicy.RateDiscountPolicy

object MovieDiscountPolicy {

    private val EARLY_TIMES = listOf(9, 10, 11)
    private val LATE_TIMES = listOf(20, 21, 22, 23)
    private val MOVIE_DAYS = listOf(10, 20, 30)
    private const val MOVIE_DAY_DISCOUNT_RATE = 0.1
    private const val EARLY_LATE_TIME_DISCOUNT_AMOUNT = 2000

    val policies = listOf(
        RateDiscountPolicy(
            listOf(DiscountConditionWithDays(MOVIE_DAYS)),
            MOVIE_DAY_DISCOUNT_RATE,
        ),
        AmountDiscountPolicy(
            listOf(DiscountConditionWithTimes(EARLY_TIMES), DiscountConditionWithTimes(LATE_TIMES)),
            EARLY_LATE_TIME_DISCOUNT_AMOUNT,
        ),
    )
}

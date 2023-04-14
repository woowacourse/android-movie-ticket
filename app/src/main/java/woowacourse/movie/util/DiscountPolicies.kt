package woowacourse.movie.util

import woowacourse.movie.model.policy.MorningPolicy
import woowacourse.movie.model.policy.MovieDayPolicy
import woowacourse.movie.model.policy.NightPolicy

object DiscountPolicies {
    val policies = listOf(MovieDayPolicy(), MorningPolicy(), NightPolicy())
}
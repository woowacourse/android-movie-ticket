package woowacourse.movie.domain.policy

object DiscountPolicies {
    val policies = listOf(MovieDayPolicy(), MorningPolicy(), NightPolicy())
}

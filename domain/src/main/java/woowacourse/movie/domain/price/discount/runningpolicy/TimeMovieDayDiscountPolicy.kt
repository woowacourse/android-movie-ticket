package woowacourse.movie.domain.price.discount.runningpolicy

import woowacourse.movie.domain.datetime.ScreeningDateTime
import woowacourse.movie.domain.price.discount.partialpolicy.DiscountPolicy
import woowacourse.movie.domain.price.discount.partialpolicy.EarlyMorningLateNightDiscount
import woowacourse.movie.domain.price.discount.partialpolicy.MovieDayDiscount

class TimeMovieDayDiscountPolicy(private val bookedScreeningDateTime: ScreeningDateTime) :
    CompoundPolicy {
    override fun getDiscountPolicies(): MutableList<DiscountPolicy> {
        val discountPolicies = mutableListOf<DiscountPolicy>()
        if (bookedScreeningDateTime.checkMovieDay()) discountPolicies.add(MovieDayDiscount())
        if (bookedScreeningDateTime.checkEarlyMorningLateNight()) discountPolicies.add(
            EarlyMorningLateNightDiscount()
        )
        return discountPolicies
    }
}

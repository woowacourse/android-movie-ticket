package woowacourse.movie.repository

import woowacourse.movie.domain.discount.*
import java.time.LocalTime

object DiscountPolicyRepository {

    private val discountPolicies: MutableList<DiscountPolicy> = mutableListOf()

    init {
        addMovieDayDiscountPolicy()
        addEarlyMorningAndLateNightDiscountPolicy()
    }

    private fun addMovieDayDiscountPolicy() {
        val movieDays = listOf(10, 20, 30)
        val discountCondition = DayDiscountCondition(movieDays)
        val movieDaysDiscountRate = 10
        val discountPolicy = RateDiscountPolicy(listOf(discountCondition), 1, movieDaysDiscountRate)
        discountPolicies.add(discountPolicy)
    }

    private fun addEarlyMorningAndLateNightDiscountPolicy() {
        val earlyMorningTimeRange = TimeRange(LocalTime.MIN, LocalTime.of(11, 0))
        val lateNightTimeRange = TimeRange(LocalTime.of(20, 0), LocalTime.MAX)
        val discountCondition =
            ScreeningTimeDiscountCondition(listOf(earlyMorningTimeRange, lateNightTimeRange))
        val discountFee = Money(2_000)
        val discountPolicy = FixDiscountPolicy(listOf(discountCondition), 2, discountFee)
        discountPolicies.add(discountPolicy)
    }

    fun findAll(): List<DiscountPolicy> {
        return discountPolicies.toList()
    }
}

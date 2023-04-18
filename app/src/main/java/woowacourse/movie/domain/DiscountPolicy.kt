package woowacourse.movie.domain

import java.time.LocalDateTime
import java.time.LocalTime

enum class DiscountPolicy {
    MOVIE_DAY {
        override val discountCondition: DiscountCondition = createDiscountCondition()

        private fun createDiscountCondition(): DiscountCondition {
            val movieDays: List<Int> = listOf(10, 20, 30)

            return DayDiscountCondition(movieDays)
        }

        override fun calculateDiscountFee(movieFee: Money): Money {
            val movieDaysDiscountRate = 10
            return movieFee / movieDaysDiscountRate
        }
    },
    SCREENING_TIME {
        override val discountCondition: DiscountCondition = createDiscountCondition()

        private fun createDiscountCondition(): DiscountCondition {
            val earlyMorningDiscountTimeRange =
                TimeRange(LocalTime.MIN, LocalTime.of(11, 0))
            val nightDiscountTimeRange = TimeRange(LocalTime.of(20, 0), LocalTime.MAX)

            return ScreeningTimeDiscountCondition(
                listOf(earlyMorningDiscountTimeRange, nightDiscountTimeRange)
            )
        }

        override fun calculateDiscountFee(movieFee: Money): Money {
            val screeningTimeDiscountAmount = 2000
            return Money(screeningTimeDiscountAmount)
        }
    };

    protected abstract val discountCondition: DiscountCondition

    protected abstract fun calculateDiscountFee(movieFee: Money): Money

    private fun getDiscountFee(screeningDateTime: LocalDateTime, movieFee: Money): Money =
        if (discountCondition.isSatisfiedBy(screeningDateTime)) calculateDiscountFee(movieFee)
        else Money(0)

    companion object {
        fun getDiscountedFee(screeningDateTime: LocalDateTime, movieFee: Money): Money {
            var totalFee = movieFee
            values().forEach {
                totalFee -= it.getDiscountFee(screeningDateTime, movieFee)
                println(totalFee)
            }
            return totalFee
        }
    }
}

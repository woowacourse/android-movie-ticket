package woowacourse.movie.domain

import java.time.LocalDateTime
import java.time.LocalTime

enum class DiscountPolicy(private val discountCondition: DiscountCondition) {
    MOVIE_DAY(DayDiscountCondition(getMovieDays())) {
        private val discountRate = 10

        override fun calculateDiscountFee(initialFee: Money, peopleCount: Int): Money =
            initialFee / discountRate
    },
    SCREENING_TIME(
        ScreeningTimeDiscountCondition(
            listOf(getEarlyMorningDiscountTimeRange(), getNightDiscountTimeRange())
        )
    ) {
        private val discountAmount = 2000

        override fun calculateDiscountFee(initialFee: Money, peopleCount: Int): Money =
            Money(peopleCount * discountAmount)
    };

    protected abstract fun calculateDiscountFee(initialFee: Money, peopleCount: Int): Money

    private fun getDiscountFee(
        dateTime: LocalDateTime,
        initialFee: Money,
        peopleCount: Int
    ): Money =
        if (discountCondition.isSatisfiedBy(dateTime)) calculateDiscountFee(initialFee, peopleCount)
        else Money(0)

    companion object {
        fun getDiscountedFee(dateTime: LocalDateTime, initialFee: Money, peopleCount: Int): Money {
            var totalFee = initialFee
            values().forEach {
                totalFee -= it.getDiscountFee(dateTime, initialFee, peopleCount)
            }
            return totalFee
        }
    }
}

private fun getMovieDays(): List<Int> = listOf(10, 20, 30)

private fun getEarlyMorningDiscountTimeRange() = TimeRange(LocalTime.MIN, LocalTime.of(11, 0))

private fun getNightDiscountTimeRange() = TimeRange(LocalTime.of(20, 0), LocalTime.MAX)

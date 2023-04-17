package woowacourse.movie.domain

import java.time.LocalTime

enum class DiscountPolicy {
    MOVIE_DAY {
        override val discountCondition: DiscountCondition = createDiscountCondition()

        private fun createDiscountCondition(): DiscountCondition {
            val movieDays: List<Int> = listOf(10, 20, 30)

            return DayDiscountCondition(movieDays)
        }

        override fun calculateDiscountFee(reservation: Reservation): Money {
            val movieDaysDiscountRate = 10
            return reservation.initReservationFee / movieDaysDiscountRate
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

        override fun calculateDiscountFee(reservation: Reservation): Money {
            val screeningTimeDiscountAmount = 2000
            return Money(reservation.peopleCount * screeningTimeDiscountAmount)
        }
    };

    protected abstract val discountCondition: DiscountCondition

    protected abstract fun calculateDiscountFee(reservation: Reservation): Money

    private fun getDiscountFee(reservation: Reservation): Money =
        if (discountCondition.isSatisfiedBy(reservation)) calculateDiscountFee(reservation)
        else Money(0)

    companion object {
        fun getDiscountedFee(reservation: Reservation): Money {
            var totalFee = reservation.initReservationFee
            values().forEach {
                totalFee -= it.getDiscountFee(reservation)
            }
            return totalFee
        }
    }
}

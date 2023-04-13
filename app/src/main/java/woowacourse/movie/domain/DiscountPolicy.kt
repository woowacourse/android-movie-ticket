package woowacourse.movie.domain

import java.time.LocalTime

enum class DiscountPolicy(private val discountCondition: DiscountCondition) {
    MOVIE_DAY(DayDiscountCondition(getMovieDays())) {
        override fun calculateDiscountFee(reservation: Reservation): Money =
            reservation.initReservationFee / getMovieDaysDiscountRate()
    },
    SCREENING_TIME(
        ScreeningTimeDiscountCondition(
            listOf(getEarlyMorningDiscountTimeRange(), getNightDiscountTimeRange())
        )
    ) {
        override fun calculateDiscountFee(reservation: Reservation): Money =
            Money(reservation.peopleCount * getScreeningTimeDiscountAmount())
    };

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

private fun getMovieDays(): List<Int> = listOf(10, 20, 30)

private fun getMovieDaysDiscountRate(): Int = 10

private fun getEarlyMorningDiscountTimeRange() = TimeRange(LocalTime.MIN, LocalTime.of(11, 0))

private fun getNightDiscountTimeRange() = TimeRange(LocalTime.of(20, 0), LocalTime.MAX)

private fun getScreeningTimeDiscountAmount(): Int = 2000

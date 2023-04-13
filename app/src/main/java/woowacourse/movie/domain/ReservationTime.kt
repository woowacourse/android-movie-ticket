package woowacourse.movie.domain

import woowacourse.movie.R

class ReservationTime(private val dayOfWeek: DayOfWeek) {

    fun getScreeningTimes(): Int = when (dayOfWeek) {
        DayOfWeek.WEEKDAY -> R.array.weekday_times
        DayOfWeek.WEEKEND -> R.array.weekend_times
    }
}

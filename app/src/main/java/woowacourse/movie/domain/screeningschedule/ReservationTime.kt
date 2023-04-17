package woowacourse.movie.domain.screeningschedule

import woowacourse.movie.R
import woowacourse.movie.domain.DayOfWeek

class ReservationTime(private val dayOfWeek: DayOfWeek) {

    fun getIntervalTimes(): Int = when (dayOfWeek) {
        DayOfWeek.WEEKDAY -> R.array.weekday_times
        DayOfWeek.WEEKEND -> R.array.weekend_times
    }
}

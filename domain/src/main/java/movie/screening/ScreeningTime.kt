package movie.screening

import java.time.LocalDate
import java.time.LocalTime

object ScreeningTime {

    private val WEEKEND_SCHEDULE: List<LocalTime> =
        (9..24 step 2).map { time -> LocalTime.of(time, 0) }
    private val WEEKDAY_SCHEDULE: List<LocalTime> =
        (10..24 step 2).map { time -> LocalTime.of(time % 24, 0) }

    fun getScreeningTime(date: LocalDate): List<LocalTime> = when {
        isWeekend(date) -> WEEKEND_SCHEDULE
        else -> WEEKDAY_SCHEDULE
    }

    private fun isWeekend(date: LocalDate): Boolean {
        val dayOfWeek = date.dayOfWeek
        return dayOfWeek == java.time.DayOfWeek.SATURDAY || dayOfWeek == java.time.DayOfWeek.SUNDAY
    }
}

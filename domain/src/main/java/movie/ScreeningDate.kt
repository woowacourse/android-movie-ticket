package movie

import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime
import java.time.temporal.ChronoUnit

class ScreeningDate(
    val startDate: LocalDate,
    val endDate: LocalDate,
) : Serializable {
    val dateList = (0..ChronoUnit.DAYS.between(startDate, endDate))
        .map { startDate.plusDays(it).toString() }

    fun getScreeningTime(date: LocalDate): List<LocalTime> = when {
        isWeekend(date) -> WEEKEND_SCHEDULE
        else -> WEEKDAY_SCHEDULE
    }

    private fun isWeekend(date: LocalDate): Boolean {
        val dayOfWeek = date.dayOfWeek
        return dayOfWeek == java.time.DayOfWeek.SATURDAY || dayOfWeek == java.time.DayOfWeek.SUNDAY
    }

    companion object {
        private val WEEKEND_SCHEDULE: List<LocalTime> =
            (9..24 step 2).map { time -> LocalTime.of(time, 0) }
        private val WEEKDAY_SCHEDULE: List<LocalTime> =
            (10..24 step 2).map { time -> LocalTime.of(time % 24, 0) }
    }
}

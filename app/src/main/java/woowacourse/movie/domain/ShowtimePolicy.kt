package woowacourse.movie.domain

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

class ShowtimePolicy {
    fun showtimes(date: LocalDate): List<LocalTime> {
        if (date.isHoliday) {
            return holidayShowtimes
        }
        return workingDayShowtimes
    }

    private val LocalDate.isHoliday: Boolean get() = dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY

    private val workingDayShowtimes: List<LocalTime> =
        (WORKING_DAY_START_HOUR until END_HOUR step HOUR_INTERVAL).map { hour: Int ->
            LocalTime.of(
                hour,
                0,
            )
        }

    private val holidayShowtimes: List<LocalTime> =
        (HOLIDAY_START_HOUR until END_HOUR step HOUR_INTERVAL).map { hour: Int ->
            LocalTime.of(
                hour,
                0,
            )
        }

    companion object {
        const val WORKING_DAY_START_HOUR = 10
        const val HOLIDAY_START_HOUR = 9
        const val HOUR_INTERVAL = 2
        const val END_HOUR = 24
    }
}

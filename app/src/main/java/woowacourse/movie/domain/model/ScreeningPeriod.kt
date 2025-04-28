package woowacourse.movie.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Parcelize
data class ScreeningPeriod(
    val startDate: LocalDate,
    val endDate: LocalDate,
) : Parcelable {
    fun getAvailableDates(now: LocalDateTime): List<LocalDate> {
        val dates = mutableListOf<LocalDate>()
        var date = now.toLocalDate()
        while (!date.isAfter(endDate)) {
            dates.add(date)
            date = date.plusDays(1)
        }
        return dates.filterNot { it.isBefore(startDate) }
    }

    fun getAvailableTimesFor(
        now: LocalDateTime,
        date: LocalDate,
    ): List<LocalTime> {
        if (now.toLocalDate().isAfter(endDate)) return emptyList()

        val isToday = date == now.toLocalDate()
        val currentTime = if (isToday) now.toLocalTime() else LocalTime.MIN

        val isWeekend = date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY
        val startHour = if (isWeekend) WEEKEND_SCREENING_START else WEEKDAY_SCREENING_START

        return (startHour until FINISH_HOUR step SCREENING_INTERVAL_TIME)
            .map { LocalTime.of(it, 0) }
            .filter { it.isAfter(currentTime) }
    }

    companion object {
        private val WEEKEND_SCREENING_START = 9
        private val WEEKDAY_SCREENING_START = 10
        private val FINISH_HOUR = 24
        private val SCREENING_INTERVAL_TIME = 2
    }
}

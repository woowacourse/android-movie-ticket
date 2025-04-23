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
        return dates
    }

    fun getAvailableTimesFor(date: LocalDate): List<LocalTime> {
        val nowDateTime = LocalDateTime.now()
        val isToday = date == nowDateTime.toLocalDate()
        val currentTime = if (isToday) nowDateTime.toLocalTime() else LocalTime.MIN

        val isWeekend = date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY
        val startHour = if (isWeekend) 9 else 10

        return (startHour until 24 step 2)
            .map { LocalTime.of(it, 0) }
            .filter { it.isAfter(currentTime) }
    }
}

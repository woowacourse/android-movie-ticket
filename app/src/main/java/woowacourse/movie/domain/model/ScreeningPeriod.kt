package woowacourse.movie.domain.model

import java.io.Serializable
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class ScreeningPeriod(
    val startDate: LocalDate,
    val endDate: LocalDate,
) : Serializable {
    init {
        require(!startDate.isAfter(endDate)) {
            INVALID_DATE_RANGE_MESSAGE.format(startDate, endDate)
        }
    }

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
        val startHour = if (isWeekend) WEEKEND_START_HOUR else WEEKDAY_START_HOUR

        return (startHour until END_HOUR step INTERVAL_HOUR)
            .map { LocalTime.of(it, 0) }
            .filter { it.isAfter(currentTime) }
    }

    companion object {
        private const val WEEKDAY_START_HOUR = 10
        private const val WEEKEND_START_HOUR = 9
        private const val END_HOUR = 24
        private const val INTERVAL_HOUR = 2

        private const val INVALID_DATE_RANGE_MESSAGE =
            "시작 날짜는 종료 날짜 이후일 수 없습니다 (startDate: %s, endDate: %s)"
    }
}

package woowacourse.movie.domain

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit

class MovieScheduler {
    fun reservableDates(
        screeningDate: ScreeningDate,
        currentDate: LocalDate,
    ): List<LocalDate> {
        val startDate = getStartDate(screeningDate.startDate, currentDate)
        return getDatesBetween(startDate, screeningDate.endDate)
    }

    fun getStartDate(
        movieStartDate: LocalDate,
        currentDate: LocalDate,
    ): LocalDate {
        return if (movieStartDate < currentDate) {
            currentDate
        } else {
            movieStartDate
        }
    }

    private fun getDatesBetween(
        startDate: LocalDate,
        endDate: LocalDate,
    ): List<LocalDate> {
        val dayDiff = ChronoUnit.DAYS.between(startDate, endDate)

        return (0..dayDiff).map { startDate.plusDays(it) }
    }

    fun reservableTimes(
        selectedDate: LocalDate,
        currentDate: LocalDateTime,
    ): List<LocalTime> {
        val isToday = selectedDate == currentDate.toLocalDate()
        val screeningDayType = ScreeningDayType.of(selectedDate)
        val startTime =
            if (!isToday) {
                screeningDayType.startTime
            } else {
                currentDate.toLocalTime().coerceAtLeast(screeningDayType.startTime)
            }
        return getTimesBetween(startTime, screeningDayType)
    }

    private fun getTimesBetween(
        startTime: LocalTime,
        screeningDayType: ScreeningDayType,
        endTime: LocalTime = END_TIME,
    ): List<LocalTime> {
        val hourDiff = ChronoUnit.HOURS.between(startTime, endTime)

        val hours = (0..hourDiff).map { startTime.plusHours(it) }

        return when (screeningDayType) {
            ScreeningDayType.WEEKDAY -> hours.filter { it.hour % 2 == 0 }
            ScreeningDayType.WEEKEND -> hours.filter { it.hour % 2 == 1 }
        }
    }

    companion object {
        private val END_TIME = LocalTime.of(23, 59)
    }
}

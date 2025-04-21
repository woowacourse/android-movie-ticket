package woowacourse.movie.domain

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit

class MovieTimeScheduler : TimeScheduler {
    override fun reservableTimes(
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
        val startHourTime = startTime.withMinute(0).withSecond(0).withNano(0)
        if (startTime > endTime) return listOf()

        val hourDiff = ChronoUnit.HOURS.between(startHourTime, endTime)

        val hours = (1..hourDiff).map { startHourTime.plusHours(it) }

        return when (screeningDayType) {
            ScreeningDayType.WEEKDAY -> hours.filter { it.hour % 2 == 0 }
            ScreeningDayType.WEEKEND -> hours.filter { it.hour % 2 == 1 }
        }
    }

    companion object {
        private val END_TIME = LocalTime.of(23, 59)
    }
}

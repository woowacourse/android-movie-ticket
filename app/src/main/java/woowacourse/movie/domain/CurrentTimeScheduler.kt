package woowacourse.movie.domain

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit

class CurrentTimeScheduler : TimeScheduler {
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
        if (startTime > endTime) return emptyList()

        val startHourTime = roundDownToHour(startTime)
        val hourDiff = ChronoUnit.HOURS.between(startHourTime, endTime)
        val hours = generateHours(startTime, startHourTime, hourDiff)

        return filterByDayType(hours, screeningDayType)
    }

    private fun roundDownToHour(time: LocalTime): LocalTime {
        return time.withMinute(0).withSecond(0).withNano(0)
    }

    private fun generateHours(
        originalStart: LocalTime,
        roundedStart: LocalTime,
        hourDiff: Long,
    ): List<LocalTime> {
        val isPartialHourPassed = roundedStart < originalStart
        val startOffset = if (isPartialHourPassed) 1 else 0
        return (startOffset..hourDiff).map { roundedStart.plusHours(it) }
    }

    private fun filterByDayType(
        hours: List<LocalTime>,
        dayType: ScreeningDayType,
    ): List<LocalTime> {
        return when (dayType) {
            ScreeningDayType.WEEKDAY -> hours.filter { it.hour % 2 == 0 }
            ScreeningDayType.WEEKEND -> hours.filter { it.hour % 2 == 1 }
        }
    }

    companion object {
        private val END_TIME = LocalTime.of(23, 59)
    }
}

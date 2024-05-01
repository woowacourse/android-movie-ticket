package woowacourse.movie.model

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

class ScreeningDateTimeSystem(
    private val weekStartTime: Int = WEEK_START_TIME,
    private val weekendStartTime: Int = WEEKEND_START_TIME,
    private val screeningTimeInterval: Int = SCREENING_TIME_INTERVAL,
) {
    fun generate(
        startDate: LocalDate,
        endDate: LocalDate,
    ): List<ScreenDateTime> {
        val resultList = mutableListOf<ScreenDateTime>()

        var currentDate = startDate
        while (currentDate <= endDate) {
            resultList.add(createScreenDateTimeFor(currentDate))
            currentDate = currentDate.plusDays(SCREENING_DATE_INTERVAL)
        }
        return resultList
    }

    private fun createScreenDateTimeFor(date: LocalDate): ScreenDateTime {
        val startTime = determineStartTimeFor(date)
        val timeSlots = generateTimeSlotsFrom(startTime)
        return ScreenDateTime(date, timeSlots)
    }

    private fun determineStartTimeFor(date: LocalDate): Int {
        return if (date.isWeekend()) weekendStartTime else weekStartTime
    }

    private fun LocalDate.isWeekend() = this.dayOfWeek == DayOfWeek.SATURDAY || this.dayOfWeek == DayOfWeek.SUNDAY

    private fun generateTimeSlotsFrom(startHour: Int): List<LocalTime> {
        return (startHour until MID_NIGHT step screeningTimeInterval).map { hour ->
            LocalTime.of(hour, DEFAULT_MINUTE)
        } + LocalTime.MIDNIGHT
    }

    companion object {
        private const val WEEK_START_TIME = 10
        private const val WEEKEND_START_TIME = 9
        private const val DEFAULT_MINUTE = 0
        private const val SCREENING_TIME_INTERVAL = 2
        private const val SCREENING_DATE_INTERVAL = 1L
        private const val MID_NIGHT = 24
    }
}

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
        while (currentDate.isBefore(endDate) || currentDate.isEqual(endDate)) {
            val isWeekend =
                currentDate.dayOfWeek == DayOfWeek.SATURDAY || currentDate.dayOfWeek == DayOfWeek.SUNDAY
            val startTime = if (isWeekend) weekendStartTime else weekStartTime
            val timeSlots = createTimes(startTime)
            resultList.add(ScreenDateTime(currentDate, timeSlots))

            currentDate = currentDate.plusDays(SCREENING_DATE_INTERVAL)
        }
        return resultList
    }

    private fun createTimes(startHour: Int): List<LocalTime> {
        val range = (startHour..MID_NIGHT step screeningTimeInterval)
        return range.map { hour ->
            if (hour == MID_NIGHT) {
                LocalTime.of(ANOTHER_MID_NIGHT, DEFAULT_MINUTE)
            } else {
                LocalTime.of(hour, DEFAULT_MINUTE)
            }
        }.toList()
    }

    companion object {
        private const val WEEK_START_TIME = 10
        private const val WEEKEND_START_TIME = 9
        private const val DEFAULT_MINUTE = 0
        private const val SCREENING_TIME_INTERVAL = 2
        private const val SCREENING_DATE_INTERVAL = 1L
        private const val MID_NIGHT = 24
        private const val ANOTHER_MID_NIGHT = 0
    }
}

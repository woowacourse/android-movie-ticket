package woowacourse.movie.model

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

object ScreeningTimeSchedule {
    private val WEEKDAY_START_TIME = LocalTime.of(10, 0)
    private val WEEKEND_START_TIME = LocalTime.of(9, 0)
    private val WEEKDAY_END_TIME = LocalTime.of(2, 0)
    private val WEEKEND_END_TIME = LocalTime.of(1, 0)
    private const val TIME_SLOT_INTERVAL = 2L

    fun generateAvailableTimeSlots(date: LocalDate): List<LocalTime> {
        var currentTime = if (isWeekend(date)) WEEKEND_START_TIME else WEEKDAY_START_TIME
        val endTime = if (isWeekend(date)) WEEKEND_END_TIME else WEEKDAY_END_TIME
        val timeSlots = mutableListOf<LocalTime>()
        while (currentTime != endTime) {
            timeSlots.add(currentTime)
            currentTime = currentTime.plusHours(TIME_SLOT_INTERVAL)
        }
        return timeSlots
    }

    private fun isWeekend(date: LocalDate): Boolean = date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY
}

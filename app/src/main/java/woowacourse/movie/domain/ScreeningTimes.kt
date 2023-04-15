package woowacourse.movie.domain

import java.time.DayOfWeek.*
import java.time.LocalDate
import java.time.LocalTime

object ScreeningTimes {
    private const val WEEKDAY_START = 10
    private const val WEEKEND_START = 9
    private const val END = 23
    private const val TIME_STEP = 2

    private fun getHours(
        startTime: Int,
        endTime: Int,
        timeStep: Int,
    ): List<LocalTime> {
        return (startTime..endTime step timeStep).map { LocalTime.of(it, 0) }
    }

    fun getScreeningTime(date: LocalDate): List<LocalTime> = when (date.dayOfWeek) {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> getHours(WEEKDAY_START, END, TIME_STEP)
        SATURDAY, SUNDAY -> getHours(WEEKEND_START, END, TIME_STEP)
        null -> throw NullPointerException()
    }

    fun getScreeningDates(start: LocalDate, end: LocalDate): List<LocalDate> {
        val numberOfDays: Int = start.until(end).days
        return (0..numberOfDays).map { (start.plusDays(it.toLong())) }
    }
}

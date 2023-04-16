package com.woowacourse.movie.domain

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

class RunningTime {
    private fun isWeekend(date: LocalDate): Boolean =
        date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY

    private fun isToday(date: LocalDate): Boolean = date.compareTo(LocalDate.now()) == 0

    fun runningTimes(
        date: LocalDate,
        currentTime: LocalTime = LocalTime.now(),
    ): List<LocalTime> {
        val runningTimes = if (isWeekend(date)) weekendTimes else weekDayTimes
        val time = if (isToday(date)) currentTime else LocalTime.of(0, 0)

        return runningTimes.filter { it > time }
    }

    companion object {
        private val weekDayTimes = (10..24 step 2).map { hour -> LocalTime.of(hour % 24, 0, 0) }
        private val weekendTimes = (9 until 24 step 2).map { hour -> LocalTime.of(hour, 0, 0) }
    }
}

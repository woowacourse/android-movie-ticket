package com.woowacourse.movie.domain

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class Movie(
    val id: Int,
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
    val introduce: String,
) {
    fun reserveMovie(
        reserveDateTime: LocalDateTime,
        tickets: Set<Ticket>
    ): Reservation? {
        if (reserveDateTime.toLocalDate() !in getRunningDates(startDate)
        ) return null
        return Reservation(this, reserveDateTime, Tickets(tickets))
    }

    fun getRunningDates(
        today: LocalDate = LocalDate.now()
    ): List<LocalDate> {
        if (today > endDate) return emptyList()
        return generateSequence(today max startDate) { it.plusDays(1) }
            .takeWhile { it <= endDate }
            .map { it }
            .toList()
    }

    fun getRunningTimes(
        date: LocalDate,
        currentTime: LocalTime = LocalTime.now()
    ): List<LocalTime> {
        val runningTimes = if (isWeekend(date)) weekendTimes else weekDayTimes
        val time = if (isToday(date)) currentTime else LocalTime.of(0, 0)

        return runningTimes.filter { it > time }
    }

    private fun isWeekend(date: LocalDate): Boolean =
        date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY

    private fun isToday(date: LocalDate): Boolean = date.compareTo(LocalDate.now()) == 0

    private infix fun LocalDate.max(other: LocalDate): LocalDate {
        if (this > other) return this
        return other
    }

    companion object {
        private val weekDayTimes = (10..24 step 2).map { hour -> LocalTime.of(hour % 24, 0, 0) }
        private val weekendTimes = (9 until 24 step 2).map { hour -> LocalTime.of(hour, 0, 0) }
    }
}

package com.example.domain.dateTime

import java.time.LocalDate
import java.time.LocalTime

class RunningTime {
    fun getRunningTimes(date: LocalDate): List<LocalTime> {
        return when (date.dayOfWeek.value) {
            in weekDays -> weekendTimes.toList()
            else -> weekDayTimes.toList()
        }
    }

    companion object {
        private val weekDayTimes = (10..24 step 2).map { hour -> LocalTime.of(hour % 24, 0, 0) }

        private val weekendTimes = (9 until 24 step 2).map { hour -> LocalTime.of(hour, 0, 0) }

        private val weekDays = listOf(6, 7)
    }
}

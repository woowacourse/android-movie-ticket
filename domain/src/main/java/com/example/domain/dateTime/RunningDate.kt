package com.example.domain.dateTime

import java.time.LocalDate
import java.time.temporal.ChronoUnit.DAYS

class RunningDate {
    fun getRunningDates(start: LocalDate, end: LocalDate): List<LocalDate> {
        val betweenDays = DAYS.between(start, end)
        return (ZERO..betweenDays).map { start.plusDays(it) }
    }

    companion object {
        private const val ZERO = 0
    }
}

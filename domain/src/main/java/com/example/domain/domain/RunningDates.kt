package com.example.domain.domain

import java.time.LocalDate
import java.time.temporal.ChronoUnit.DAYS

class RunningDates(private val start: LocalDate, private val end: LocalDate) {
    fun getRunningDates(): List<LocalDate> {
        val betweenDays = DAYS.between(start, end)
        val allDays = mutableListOf<LocalDate>()
        for (i in ZERO..betweenDays) {
            allDays.add(start.plusDays(i))
        }
        return allDays.toList()
    }

    companion object {
        private const val ZERO = 0
    }
}

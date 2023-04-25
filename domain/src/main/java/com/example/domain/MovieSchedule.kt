package com.example.domain

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class MovieSchedule(private val startDate: LocalDate, private val endDate: LocalDate) {
    fun getScheduleDates(): List<String> {
        return getDatesBetweenTwoDates().map {
            it.format(dateTimeFormatter)
        }
    }

    private fun getDatesBetweenTwoDates(): List<LocalDate> {
        val numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate)
        return (0..numOfDaysBetween).map {
            startDate.plusDays(it)
        }.toList()
    }

    fun getScheduleTimes(date: String): List<String> {
        val selectedDate = LocalDate.parse(date, dateTimeFormatter)
        val dayOfWeek = selectedDate.dayOfWeek.value
        if (dayOfWeek in weekend) {
            return getValidTimes(weekendTimes)
        }
        return getValidTimes(weekdayTimes)
    }

    private fun getValidTimes(times: IntRange): List<String> = (times step 2).toList().map {
        "${if (it < 10) "0$it" else it}:00"
    }

    companion object {
        private val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        private const val WEEKEND_START_TIME = 9
        private const val WEEKDAY_START_TIME = 10
        private const val END_TIME = 24

        private val weekendTimes = WEEKEND_START_TIME..END_TIME
        private val weekdayTimes = WEEKDAY_START_TIME..END_TIME

        private const val SATURDAY = 6
        private const val SUNDAY = 7
        private val weekend = SATURDAY..SUNDAY
    }
}

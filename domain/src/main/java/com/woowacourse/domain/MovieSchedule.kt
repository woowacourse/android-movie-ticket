package com.woowacourse.domain

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class MovieSchedule(private val startDate: LocalDate, private val endDate: LocalDate) {

    fun getScheduleDates(): List<String> = getDatesBetweenTwoDates().map {
        it.format(dateTimeFormatter)
    }

    private fun getDatesBetweenTwoDates(): List<LocalDate> {
        val numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate)

        return (0..numOfDaysBetween).map {
            startDate.plusDays(it)
        }
    }

    fun getScheduleTimes(date: String): List<String> {
        val selectedDate = LocalDate.parse(date, dateTimeFormatter)
        val dayOfWeek = selectedDate.dayOfWeek.value

        if (dayOfWeek in weekend) {
            return getValidTimes(weekendTimes)
        }
        return getValidTimes(weekdayTimes)
    }

    private fun getValidTimes(times: IntRange): List<String> =
        (times step INTERVAL_TIME).map {
            TIME_FORMAT.format(it)
        }

    companion object {
        private const val DATE_PATTERN = "yyyy-MM-dd"
        private val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN)

        private const val WEEKEND_START_TIME = 9
        private const val WEEKDAY_START_TIME = 10
        private const val END_TIME = 24

        private val weekendTimes = WEEKEND_START_TIME..END_TIME
        private val weekdayTimes = WEEKDAY_START_TIME..END_TIME

        private const val SATURDAY = 6
        private const val SUNDAY = 7
        private val weekend = SATURDAY..SUNDAY

        private const val INTERVAL_TIME = 2
        private const val TIME_FORMAT = "%02d:00"
    }
}

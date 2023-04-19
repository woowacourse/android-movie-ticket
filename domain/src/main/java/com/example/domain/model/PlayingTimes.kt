package com.example.domain.model

import com.example.domain.model.formatter.DateFormatter
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.temporal.ChronoUnit

class PlayingTimes(startDate: LocalDate, endDate: LocalDate) :
    java.io.Serializable {

    val startDateString = DateFormatter.format(startDate)
    val endDateString = DateFormatter.format(endDate)

    private val _playingDates: MutableList<LocalDate> = mutableListOf()
    val playingDates: List<LocalDate>
        get() = _playingDates.toList()

    init {
        val days = ChronoUnit.DAYS.between(startDate, endDate)
        (0..days).fold(startDate) { date, _ ->
            _playingDates.add(date)
            date.plusDays(1)
        }
    }

    fun getTimes(date: LocalDate) = buildList<LocalTime> {
        val startHour = if (isWeekends(date.dayOfWeek)) 9 else 10
        for (hour in startHour until 24 step 2) {
            add(LocalTime.of(hour, 0))
        }
    }

    private fun isWeekends(dayOfWeek: DayOfWeek): Boolean =
        dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY
}

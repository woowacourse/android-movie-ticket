package woowacourse.movie.model

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

class Booking(
    private val movie: Movie,
    private val today: LocalDate = LocalDate.now(),
    private val currentTime: LocalTime = LocalTime.now(),
) {
    private val weekdayScreeningTimes = List(8) { idx -> LocalTime.of(9, 0).plusHours(idx * 2L) }
    private val weekendScreeningTimes = List(8) { idx -> LocalTime.of(10, 0).plusHours(idx * 2L) }

    fun screeningPeriods(): List<LocalDate> {
        val startDate =
            if (movie.screeningStartDate.isBefore(today)) today else movie.screeningStartDate
        return generateSequence(startDate) { currentDate ->
            val next = currentDate.plusDays(1)
            if (next.isAfter(movie.screeningEndDate)) null else next
        }.toList()
    }

    fun screeningTimes(selectedDate: LocalDate): List<LocalTime> {
        if (today == selectedDate) return screeningTimesType(today, currentTime)
        return screeningTimesType(selectedDate)
    }

    private fun screeningTimesType(
        date: LocalDate,
        baseTime: LocalTime = LocalTime.of(8, 0),
    ): List<LocalTime> {
        if (isWeekend(date)) {
            return weekendScreeningTimes.filter { time ->
                time == LocalTime.MIDNIGHT ||
                    time.isAfter(
                        baseTime,
                    )
            }.toList()
        }
        return weekdayScreeningTimes.filter { time -> time.isAfter(baseTime) }
    }

    private fun isWeekend(date: LocalDate): Boolean {
        return when (date.dayOfWeek) {
            DayOfWeek.SATURDAY, DayOfWeek.SUNDAY -> true
            else -> false
        }
    }
}
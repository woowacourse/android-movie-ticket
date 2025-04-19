package woowacourse.movie.model

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class Booking(
    private val movie: Movie,
    private val today: LocalDate = LocalDate.now(),
    private val currentTime: LocalTime = LocalTime.now(),
) {
    private val weekdayScreeningTimes = List(8) { idx -> LocalTime.of(9, 0).plusHours(idx * 2L) }
    private val weekendScreeningTimes = List(8) { idx -> LocalTime.of(10, 0).plusHours(idx * 2L) }

    fun screeningPeriods(): List<String> {
        val startDate =
            if (movie.screeningStartDate.isBefore(today)) today else movie.screeningStartDate
        return generateSequence(startDate) { currentDate ->
            val next = currentDate.plusDays(1)
            if (next.isAfter(movie.screeningEndDate)) null else next
        }.map { date -> formatDate(date) }.toList()
    }

    fun screeningTimes(date: String): List<String> {
        val selectedDate = localDate(date)
        if (today == selectedDate) return screeningTimesType(today, currentTime)
        return screeningTimesType(selectedDate)
    }

    private fun screeningTimesType(
        date: LocalDate,
        baseTime: LocalTime = LocalTime.of(8, 0),
    ): List<String> {
        if (isWeekend(date)) {
            return weekendScreeningTimes.filter { time ->
                time == LocalTime.MIDNIGHT ||
                        time.isAfter(
                            baseTime,
                        )
            }
                .map { time -> formatTime(time) }
        }
        return weekdayScreeningTimes.filter { time -> time.isAfter(baseTime) }
            .map { time -> formatTime(time) }
    }

    private fun localDate(date: String): LocalDate {
        val (year, month, day) = date.split("-").map { it.toInt() }
        return LocalDate.of(year, month, day)
    }

    private fun isWeekend(date: LocalDate): Boolean {
        return when (date.dayOfWeek) {
            DayOfWeek.SATURDAY, DayOfWeek.SUNDAY -> true
            else -> false
        }
    }

    private fun formatDate(date: LocalDate): String {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }

    private fun formatTime(time: LocalTime): String {
        return time.format(DateTimeFormatter.ofPattern("kk:mm"))
    }
}

package woowacourse.movie.util

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object DateTimeUtil {
    const val MOVIE_DATE_FORMAT = "yyyy.M.d"
    const val SPINNER_DATE_FORMAT = "yyyy-M-d"
    const val MOVIE_TIME_FORMAT = "kk:mm"

    fun toFormattedString(
        date: LocalDate,
        pattern: String,
    ): String {
        return date.format(DateTimeFormatter.ofPattern(pattern))
    }

    fun toFormattedString(
        time: LocalTime,
        pattern: String,
    ): String {
        return time.format(DateTimeFormatter.ofPattern(pattern))
    }

    fun toLocalDate(
        date: String,
        delimiter: String,
    ): LocalDate {
        val (year, month, day) = date.split(delimiter).map { it.toInt() }
        return LocalDate.of(year, month, day)
    }

    fun toLocalTime(
        time: String,
        delimiter: String,
    ): LocalTime {
        val (hour, minute) = time.split(delimiter).map { it.toInt() }
        return LocalTime.of(hour, minute)
    }

    fun toSpinnerDates(dates: List<LocalDate>): List<String> {
        return dates.map { date -> toFormattedString(date, SPINNER_DATE_FORMAT) }
    }

    fun toSpinnerTimes(times: List<LocalTime>): List<String> {
        return times.map { time -> toFormattedString(time, MOVIE_TIME_FORMAT) }
    }
}

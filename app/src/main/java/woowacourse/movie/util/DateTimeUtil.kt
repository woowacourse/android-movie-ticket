package woowacourse.movie.util

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object DateTimeUtil {
    const val MOVIE_SPINNER_DATE_DELIMITER = "-"
    const val MOVIE_DATE_DELIMITER = "."
    const val MOVIE_TIME_DELIMITER = ":"
    const val MOVIE_DATE_FORMAT = "yyyy.M.d"
    const val MOVIE_TIME_FORMAT = "kk:mm"
    private const val SPINNER_DATE_FORMAT = "yyyy-MM-dd"

    fun LocalDate.toFormattedString(
        pattern: String,
    ): String {
        return this.format(DateTimeFormatter.ofPattern(pattern))
    }

    fun LocalTime.toFormattedString(
        pattern: String,
    ): String {
        return this.format(DateTimeFormatter.ofPattern(pattern))
    }

    fun String.toLocalDate(
        delimiter: String,
    ): LocalDate {
        val (year, month, day) = this.split(delimiter).map { it.toInt() }
        return LocalDate.of(year, month, day)
    }

    fun String.toLocalTime(
        delimiter: String,
    ): LocalTime {
        val (hour, minute) = this.split(delimiter).map { it.toInt() }
        return LocalTime.of(hour, minute)
    }

    fun toSpinnerDates(dates: List<LocalDate>): List<String> {
        return dates.map { date -> date.toFormattedString(SPINNER_DATE_FORMAT) }
    }

    fun toSpinnerTimes(times: List<LocalTime>): List<String> {
        return times.map { time -> time.toFormattedString(MOVIE_TIME_FORMAT) }
    }
}

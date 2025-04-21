package woowacourse.movie.util

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object DateTimeUtil {
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
}

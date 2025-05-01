package woowacourse.movie.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Formatter {
    private val DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    private val DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")

    fun format(date: LocalDate): String = DATE_FORMATTER.format(date)

    fun format(dateTime: LocalDateTime): String = DATE_TIME_FORMATTER.format(dateTime)
}

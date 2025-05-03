package woowacourse.movie.common

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object StringFormatter {
    fun date(date: LocalDate): String = date.format(DATE_FORMAT)

    fun dateTime(dateTime: LocalDateTime): String = dateTime.format(DATE_TIME_FORMAT)

    private val DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    private val DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")
}

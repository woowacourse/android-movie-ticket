package woowacourse.movie

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateFormatter {
    fun format(date: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern(DATE_PATTERN)
        return date.format(formatter)
    }

    fun format(date: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofPattern(DATETIME_PATTERN)
        return date.format(formatter)
    }

    companion object {
        private const val DATE_PATTERN = "yyyy.M.d"
        private const val DATETIME_PATTERN = "yyyy.M.d. HH:mm"
    }
}
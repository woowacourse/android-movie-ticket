package woowacourse.movie.fomatter

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateAndTimeFormatter {
    fun active(dateTime: LocalDateTime): String {
        return dateTime.format(DATE_TIME_FORMATTER)
    }

    companion object {
        private val DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy.M.d HH:mm")
    }
}

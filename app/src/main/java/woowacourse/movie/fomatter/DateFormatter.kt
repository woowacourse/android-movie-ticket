package woowacourse.movie.fomatter

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DateFormatter {
    fun active(date: LocalDate): String {
        return date.format(DATE_FORMATTER)
    }

    companion object {
        val DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.M.d")
    }
}

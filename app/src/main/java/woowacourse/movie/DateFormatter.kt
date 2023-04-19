package woowacourse.movie

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateFormatter {
    private const val DATE_FORMAT = "yyyy.M.d"

    fun format(targetDate: LocalDate): String {
        val date = LocalDate.parse(targetDate.toString(), DateTimeFormatter.ISO_LOCAL_DATE)
        val formatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
        return date.format(formatter)
    }
}

package woowacourse.movie.formatter

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateFormatter : Formatter<LocalDate>() {
    override fun formatToOriginal(string: String, format: String): LocalDate =
        LocalDate.parse(string, DateTimeFormatter.ofPattern(format))

    override fun formatToString(data: LocalDate, format: String): String {
        val dateFormatter = DateTimeFormatter.ofPattern(format)
        return dateFormatter.format(data)
    }
}

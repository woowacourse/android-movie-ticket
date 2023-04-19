package woowacourse.movie.formatter

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateFormatter : Formatter<LocalDate>() {
    override val formatString: String = "yyyy.M.d"
    override fun formatToOriginal(string: String): LocalDate =
        LocalDate.parse(string, DateTimeFormatter.ofPattern(formatString))

    override fun formatToString(data: LocalDate): String {
        val dateFormatter = DateTimeFormatter.ofPattern(formatString)
        return dateFormatter.format(data)
    }
}

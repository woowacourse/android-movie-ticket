package woowacourse.movie.formatter

import java.time.LocalTime
import java.time.format.DateTimeFormatter

object TimeFormatter : Formatter<LocalTime>() {
    override fun formatToOriginal(string: String, format: String): LocalTime = LocalTime.parse(
        string,
        DateTimeFormatter.ofPattern(
            format
        )
    )

    override fun formatToString(data: LocalTime, format: String): String {
        val timeFormatter = DateTimeFormatter.ofPattern(format)
        return timeFormatter.format(data)
    }
}

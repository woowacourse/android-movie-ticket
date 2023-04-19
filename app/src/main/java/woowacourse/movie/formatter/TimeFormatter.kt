package woowacourse.movie.formatter

import java.time.LocalTime
import java.time.format.DateTimeFormatter

object TimeFormatter : Formatter<LocalTime>() {
    override val formatString: String = "HH:mm"

    override fun formatToOriginal(string: String): LocalTime = LocalTime.parse(
        string,
        DateTimeFormatter.ofPattern(
            formatString
        )
    )

    override fun formatToString(data: LocalTime): String {
        val timeFormatter = DateTimeFormatter.ofPattern(formatString)
        return timeFormatter.format(data)
    }
}

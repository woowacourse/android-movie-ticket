package woowacourse.movie.ui

import java.time.format.DateTimeFormatter

object DateTimeFormatters {
    val hyphenDateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    val dateDotTimeColonFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")
}

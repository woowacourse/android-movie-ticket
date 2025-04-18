package woowacourse.movie

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.toFormattedString(): String {
    return DateTimeFormatter.ofPattern("yyyy.MM.dd").format(this)
}

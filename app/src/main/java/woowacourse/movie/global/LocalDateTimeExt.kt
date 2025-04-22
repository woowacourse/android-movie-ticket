package woowacourse.movie.global

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.toFormattedString(): String {
    return DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm").format(this)
}

fun LocalDateTime.toFormattedDate(): String {
    return DateTimeFormatter.ofPattern("yyyy.MM.dd").format(this)
}

fun LocalDate.toFormattedString(): String {
    return DateTimeFormatter.ofPattern("MM.dd").format(this)
}

package woowacourse.movie

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDate.toDotFormat(): String {
    return this.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
}

fun LocalDateTime.toDotFormat(): String {
    return this.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"))
}

fun String.toLocalDateFromDot(): LocalDate {
    val (year, month, day) = this.split(".").map { it.toInt() }
    return LocalDate.of(year, month, day)
}

fun String.toLocalDateFromDash(): LocalDate {
    val (year, month, day) = this.split("-").map { it.toInt() }
    return LocalDate.of(year, month, day)
}

package woowacourse.movie.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.toCustomString(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy.M.d")
    return this.format(formatter)
}

fun String.toLocalDate(): LocalDate {
    val formatter = DateTimeFormatter.ofPattern("yyyy.M.d")
    return LocalDate.parse(this, formatter)
}

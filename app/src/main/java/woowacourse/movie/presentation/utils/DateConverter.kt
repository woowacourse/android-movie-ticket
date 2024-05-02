package woowacourse.movie.presentation.utils

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun LocalDate.toCustomString(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy.M.d")
    return this.format(formatter)
}

fun LocalTime.toCustomString(): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    return this.format(formatter)
}

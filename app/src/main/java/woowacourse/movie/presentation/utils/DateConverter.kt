package woowacourse.movie.presentation.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDate.dateToString(): String = this.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))

fun LocalDateTime.dateToString(): String = this.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"))

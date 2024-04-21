package woowacourse.movie.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun formatMovieDate(date: LocalDate): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    return date.format(formatter)
}

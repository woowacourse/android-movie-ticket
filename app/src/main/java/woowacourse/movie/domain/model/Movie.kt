package woowacourse.movie.domain.model

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Movie(
    val posterSrc: Int,
    val title: String,
    val screeningDate: LocalDate,
    val runningTime: Int,
    val summary: String,
) {
    fun screeningDateToString(): String = screeningDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
}

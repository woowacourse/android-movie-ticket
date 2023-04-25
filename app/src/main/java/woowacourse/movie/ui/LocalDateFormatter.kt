package woowacourse.movie.ui

import java.time.LocalDate
import java.time.format.DateTimeFormatter

val LocalDate.formattedDate: String
    get() = this.format(DateTimeFormatter.ofPattern(MOVIE_DATE_PATTERN))

private const val MOVIE_DATE_PATTERN = "yyyy.MM.dd"

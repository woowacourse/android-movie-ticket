package woowacourse.movie.model

import java.time.LocalDate

data class Movie(
    val id: Int,
    val poster: Int,
    val title: String,
    val firstScreeningDate: LocalDate,
    val lastScreeningDate: LocalDate,
    val runningTime: String,
    val summary: String,
)

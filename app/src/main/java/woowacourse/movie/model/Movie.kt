package woowacourse.movie.model

import java.time.LocalDate

data class Movie(
    val id: Int,
    val poster: Int,
    val title: String,
    val screeningDate: LocalDate,
    val runningTime: String,
    val summary: String,
)

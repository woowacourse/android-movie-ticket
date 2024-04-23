package woowacourse.movie.domain.model

import java.time.LocalDate

data class Movie(
    val movieId: Int,
    val posterName: String,
    val title: String,
    val screeningDate: LocalDate,
    val runningTime: Int,
    val summary: String,
)

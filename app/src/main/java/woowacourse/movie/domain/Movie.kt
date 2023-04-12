package woowacourse.movie.domain

import java.time.LocalDate

data class Movie(
    val id: Long,
    val title: String,
    val screeningStartDate: LocalDate,
    val screeningEndDate: LocalDate,
    val runningTime: Int,
    val description: String,
    val thumbnail: Int,
    val poster: Int,
)

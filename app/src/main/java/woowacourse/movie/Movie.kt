package woowacourse.movie

import java.time.LocalDate

data class Movie(
    val id: Long,
    val title: String,
    val screeningDate: LocalDate,
    val runningTime: Int,
    val description: String,
    val thumbnail: Int,
    val poster: Int,
)

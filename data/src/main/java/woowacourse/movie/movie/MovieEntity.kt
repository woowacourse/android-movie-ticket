package woowacourse.movie.movie

import java.time.LocalDate

data class MovieEntity(
    val id: Long,
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
    val description: String,
)

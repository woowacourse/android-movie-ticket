package woowacourse.movie

import java.time.LocalDate

data class Movie(
    val id: Int,
    val title: String,
    val screeningDate: LocalDate,
    val runningTime: Int,
    val description: String,
)

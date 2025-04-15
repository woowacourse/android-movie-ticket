package woowacourse.movie

import java.time.LocalDate

data class Movie(
    val title: String,
    val screeningDate: LocalDate,
    val runningTime: Int,
    val posterId: Int,
)

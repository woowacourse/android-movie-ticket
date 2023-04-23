package woowacourse.movie.domain

import java.time.LocalDate

class Movie(
    val title: String,
    val screeningStartDate: LocalDate,
    val screeningEndDate: LocalDate,
    val runningTime: Minute,
    val posterResourceId: Int,
    val summary: String
)

package woowacourse.movie.domain.model

import java.time.LocalDate

data class Movie(
    val title: String,
    val startScreeningDate: LocalDate,
    val endScreeningDate: LocalDate,
    val runningTime: Int,
    val id: Int,
)

package woowacourse.movie.model.data.dto

import java.time.LocalDate

data class Movie(
    val id: Long = 0,
    val posterImageId: Int,
    val title: String,
    val startScreeningDate: LocalDate,
    val endScreeningDate: LocalDate,
    val runningTime: Int,
    val synopsis: String,
)

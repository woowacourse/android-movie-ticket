package woowacourse.movie.model

import java.time.LocalDate

data class Movie(
    val id: Int,
    val title: String,
    val thumbnail: Int,
    val date: LocalDate,
    val runningTime: Int,
    val introduction: String,
)

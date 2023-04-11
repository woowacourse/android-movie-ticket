package woowacourse.movie.domain

import java.util.Date

data class Movie(
    val picture: Int,
    val title: String,
    val date: Date,
    val runningTime: Int,
    val description: String,
)

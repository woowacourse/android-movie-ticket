package woowacourse.movie.domain

import java.io.Serializable

data class Movie(
    val title: String,
    val runningDate: RunningDate,
    val runningTime: Int,
    val description: String,
    val moviePoster: Int,
) : Serializable

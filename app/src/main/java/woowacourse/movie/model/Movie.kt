package woowacourse.movie.model

import java.io.Serializable

data class Movie(
    val id: Long = 0,
    val poster: Int,
    val title: String,
    val content: String,
    val openingDay: String,
    val runningTime: Int,
) : Serializable

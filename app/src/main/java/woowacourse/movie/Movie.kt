package woowacourse.movie

import java.io.Serializable

data class Movie(
    val poster: Int,
    val title: String,
    val releaseDate: String,
    val runningTime: String,
    val synopsis: String,
) : Serializable

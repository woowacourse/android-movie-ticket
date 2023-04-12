package woowacourse.movie.domain

import java.io.Serializable

data class Movie(
    val poster: Int,
    val title: String,
    val date: Date,
    val time: Int,
    val description: String,
) : Serializable

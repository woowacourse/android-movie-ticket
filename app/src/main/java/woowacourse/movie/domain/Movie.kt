package woowacourse.movie.domain

import java.io.Serializable

data class Movie(
    val image: Int,
    val title: String,
    val date: String,
    val time: String,
) : Serializable

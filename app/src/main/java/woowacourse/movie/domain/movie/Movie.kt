package woowacourse.movie.domain.movie

import java.io.Serializable

class Movie(
    val id: Int,
    val title: String,
    val runningTime: Int,
) : Serializable

package woowacourse.movie.domain.reservation

import java.io.Serializable

data class Movie(
    val id: Int,
    val title: String,
    val runningTime: Int,
) : Serializable

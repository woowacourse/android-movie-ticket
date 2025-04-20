package woowacourse.movie.domain.model

import java.io.Serializable

data class Movie(
    val title: String,
    val posterId: Int,
    val releaseDate: ScreeningDate,
    val runningTime: String,
) : Serializable

package woowacourse.movie.model

import java.io.Serializable

data class UiMovie(
    val poster: Int,
    val title: String,
    val content: String,
    val openingDay: String,
    val runningTime: Int,
    val price: Int = 13_000,
) : Serializable

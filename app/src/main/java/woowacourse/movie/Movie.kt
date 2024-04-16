package woowacourse.movie

import java.io.Serializable

data class Movie(
    val poster: Int,
    val title: String,
    val content: String,
    val openingDay: String,
    val runningTime: Int,
    val price: Int = 13_000,
) : Serializable

package woowacourse.movie

import java.io.Serializable

data class Movie(
    val thumbnail: String,
    val title: String,
    val description: String,
    val date: Int,
    val runningTime: Int,
) : Serializable

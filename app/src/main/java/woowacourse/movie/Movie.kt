package woowacourse.movie

import java.io.Serializable

data class Movie(
    val thumbnail: Int,
    val title: String,
    val description: String,
    val date: Long,
    val runningTime: Int,
) : Serializable

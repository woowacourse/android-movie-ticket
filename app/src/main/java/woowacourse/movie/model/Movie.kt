package woowacourse.movie.model

import java.io.Serializable

data class Movie(
    val id: Long,
    val thumbnail: Int,
    val title: String,
    val description: String,
    val date: Long,
    val runningTime: Int,
) : Serializable

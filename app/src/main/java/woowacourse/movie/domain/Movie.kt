package woowacourse.movie.domain

import java.io.Serializable
import java.time.LocalDateTime

data class Movie(
    val picture: Int,
    val title: String,
    val date: LocalDateTime,
    val runningTime: Int,
    val description: String,
) : Serializable

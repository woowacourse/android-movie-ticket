package woowacourse.movie.list

import java.io.Serializable
import java.time.LocalDateTime

data class Movie(
    val title: String,
    val posterResourceId: Int,
    val screeningDate: LocalDateTime,
    val runningTime: Int,
    val description: String,
    val id: Long,
) : Serializable

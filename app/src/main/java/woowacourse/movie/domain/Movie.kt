package woowacourse.movie.domain

import java.time.LocalDateTime

data class Movie(
    val id: Long,
    val thumbnailUrl: String,
    val title: String,
    val description: String,
    val dateTime: List<LocalDateTime>,
    val runningTime: Int,
)

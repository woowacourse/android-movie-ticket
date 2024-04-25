package woowacourse.movie.data.remote.dto

import java.time.LocalDateTime

data class MovieResponse(
    val id: Long,
    val thumbnailUrl: String,
    val title: String,
    val description: String,
    val dateTime: List<LocalDateTime>,
    val runningTime: Int,
)

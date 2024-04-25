package io.pyron.server.data.dto
import java.time.LocalDateTime

data class MovieWithDateTime(
    val id: Long,
    val thumbnailUrl: String,
    val title: String,
    val description: String,
    val dateTime: List<LocalDateTime>,
    val runningTime: Int,
)

package io.pyron.server.data.dto
import io.pyron.server.data.entity.ScreenDateTime

data class MovieWithScreenDateTime(
    val id: Long,
    val thumbnailUrl: String,
    val title: String,
    val description: String,
    val screenDateTimes: List<ScreenDateTime>,
    val runningTime: Int,
)

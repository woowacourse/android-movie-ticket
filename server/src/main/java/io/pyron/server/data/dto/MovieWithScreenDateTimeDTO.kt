package io.pyron.server.data.dto
import io.pyron.server.data.entity.ScreenDateTime

// join DTO
data class MovieWithScreenDateTimeDTO(
    val id: Long,
    val thumbnailUrl: String,
    val title: String,
    val description: String,
    val screenDateTimes: List<ScreenDateTime>,
    val runningTime: Int,
)

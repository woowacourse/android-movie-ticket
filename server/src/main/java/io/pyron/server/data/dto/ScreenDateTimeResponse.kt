package io.pyron.server.data.dto

import io.pyron.server.data.entity.ScreenDateTime
import java.time.LocalDateTime

class ScreenDateTimeResponse(
    val id: Long,
    val dateTime: LocalDateTime,
) {
    companion object {
        fun from(screenDateTime: ScreenDateTime): ScreenDateTimeResponse {
            return ScreenDateTimeResponse(
                id = screenDateTime.id,
                dateTime = screenDateTime.dateTime,
            )
        }
    }
}

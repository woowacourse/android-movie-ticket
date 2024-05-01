package woowacourse.movie.data.remote.dto

import java.time.LocalDateTime

data class ScreenDateTimeResponse(
    val id: Long,
    val dateTime: LocalDateTime,
)

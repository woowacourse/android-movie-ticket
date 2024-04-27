package woowacourse.movie.data.remote.mapper

import woowacourse.movie.data.remote.dto.ScreenDateTimeResponse
import woowacourse.movie.domain.ScreenDateTime

object ScreenDateTimeMapper {
    fun fromScreenDateTimeResponse(screenDateTimeResponse: ScreenDateTimeResponse): ScreenDateTime {
        return ScreenDateTime(
            id = screenDateTimeResponse.id,
            dateTime = screenDateTimeResponse.dateTime,
        )
    }
}

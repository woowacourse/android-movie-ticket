package woowacourse.movie.dto

import woowacourse.movie.domain.ScreeningInfoOfMovie
import java.time.LocalDateTime

data class ScreeningInfoDto(val screeningDateTime: LocalDateTime) {

    companion object {
        fun from(screeningInfo: ScreeningInfoOfMovie): ScreeningInfoDto {
            return ScreeningInfoDto(screeningInfo.screeningDateTime)
        }
    }
}

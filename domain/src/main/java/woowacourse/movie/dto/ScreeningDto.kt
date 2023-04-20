package woowacourse.movie.dto

import woowacourse.movie.domain.Screening
import java.time.LocalDateTime

data class ScreeningDto(val screeningDateTime: LocalDateTime) {
    companion object {
        fun from(screening: Screening): ScreeningDto {
            return ScreeningDto(screening.screeningDateTime)
        }
    }
}

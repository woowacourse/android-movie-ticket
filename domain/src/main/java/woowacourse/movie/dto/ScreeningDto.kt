package woowacourse.movie.dto

import woowacourse.movie.domain.ScreeningInfoOfMovie
import java.time.LocalDateTime

data class ScreeningInfoDto(
    val screeningDateTime: LocalDateTime,
    val seats: List<SeatDto>
) {

    companion object {
        fun from(screeningInfo: ScreeningInfoOfMovie): ScreeningInfoDto {
            return ScreeningInfoDto(
                screeningInfo.screeningDateTime,
                screeningInfo.movieHouse.seats.map(SeatDto::from)
            )
        }
    }
}

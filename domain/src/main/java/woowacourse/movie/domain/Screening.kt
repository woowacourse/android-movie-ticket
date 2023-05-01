package woowacourse.movie.domain

import java.time.LocalDateTime
import kotlin.properties.Delegates

class Screening(
    private val screeningRange: ScreeningRange,
    private val theater: Theater,
    private val movie: MovieData
) {
    var id: Long? by Delegates.vetoable(null) { _, old, new ->
        old == null && new != null
    }

    fun reserve(screeningDateTime: LocalDateTime, seatPoints: List<Point>): Reservation {
        require(seatPoints.isNotEmpty()) { RESERVE_WITHOUT_SEAT_POINT_ERROR }
        require(screeningRange.screenOn(screeningDateTime)) {
            NOT_SCREEN_ON_INPUT_DATE_TIME_ERROR.format(screeningDateTime)
        }
        require(seatPoints.all { theater.hasSeatOn(it) }) { RESERVE_WITH_NOT_EXIST_SEAT_ERROR }

        return Reservation(screeningDateTime, theater, seatPoints)
    }

    companion object {
        private const val NOT_SCREEN_ON_INPUT_DATE_TIME_ERROR = "%s에 상영하지 않습니다."
        private const val RESERVE_WITHOUT_SEAT_POINT_ERROR = "최소 한 개 이상의 좌석을 예매해야 합니다."
        private const val RESERVE_WITH_NOT_EXIST_SEAT_ERROR = "예매하려는 좌석이 존재하지 않습니다."
    }
}

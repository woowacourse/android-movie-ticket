package woowacourse.movie.domain.screening

import woowacourse.movie.domain.theater.Point
import woowacourse.movie.domain.theater.Theater
import java.time.LocalDateTime
import kotlin.properties.Delegates

class Screening(
    val screeningRange: ScreeningRange,
    val theater: Theater,
    val movie: Movie
) {
    var id: Long? by Delegates.vetoable(null) { _, old, new ->
        old == null && new != null
    }

    fun reserve(screeningDateTime: LocalDateTime, seatPoints: List<Point>): Reservation {
        require(screeningRange.screenOn(screeningDateTime)) {
            NOT_SCREEN_ON_INPUT_DATE_TIME_ERROR.format(screeningDateTime)
        }

        return Reservation(movie, screeningDateTime, theater, seatPoints)
    }

    companion object {
        private const val NOT_SCREEN_ON_INPUT_DATE_TIME_ERROR = "%s에 상영하지 않습니다."
    }
}

package woowacourse.movie.domain.service

import woowacourse.movie.domain.model.ReservedMovie
import java.time.LocalDateTime

class ReservationService {
    fun reserveMovie(
        movieId: Int,
        screeningDateTime: LocalDateTime,
        headCount: Int,
    ): ReservedMovie = ReservedMovie(movieId, screeningDateTime, headCount)
}

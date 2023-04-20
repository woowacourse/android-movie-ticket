package woowacourse.movie.dto

import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.ReservationResult
import java.time.LocalDateTime

data class ReservationDto(val movieTitle: String, val screeningDateTime: LocalDateTime, val audienceCount: Int, val fee: Int) {

    companion object {
        fun create(movie: Movie, reservationResult: ReservationResult): ReservationDto {
            return ReservationDto(
                movie.title,
                reservationResult.selectedDateTime,
                reservationResult.audienceCount,
                reservationResult.fee.amount
            )
        }
    }
}

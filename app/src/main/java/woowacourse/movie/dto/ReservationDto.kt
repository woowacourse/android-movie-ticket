package woowacourse.movie.dto

import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Reservation
import java.time.LocalDateTime

data class ReservationDto(val movieTitle: String, val screeningDateTime: LocalDateTime, val audienceCount: Int, val fee: Int) {

    companion object {
        fun create(movie: Movie, reservation: Reservation): ReservationDto {
            return ReservationDto(
                movie.title,
                reservation.selectedDateTime,
                reservation.audienceCount,
                reservation.fee.amount
            )
        }
    }
}

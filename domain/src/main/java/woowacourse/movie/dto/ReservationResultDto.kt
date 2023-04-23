package woowacourse.movie.dto

import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.ReservationResult
import java.time.LocalDateTime

data class ReservationResultDto(
    val movieTitle: String,
    val screeningDateTime: LocalDateTime,
    val seatPoints: List<Pair<Int, Int>>,
    val fee: Int
) {

    companion object {
        fun of(movie: Movie?, reservationResult: ReservationResult): ReservationResultDto {
            return ReservationResultDto(
                movie?.title ?: "예매한 영화가 삭제되었습니다.",
                reservationResult.screeningInfo.screeningDateTime,
                reservationResult.seatPoints.map { it.row to it.column },
                reservationResult.fee.amount
            )
        }
    }
}

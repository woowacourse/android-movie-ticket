package woowacourse.movie.domain

data class ReservationResult(
    val movieId: Long,
    val screeningInfo: ScreeningInfoOfMovie,
    val seatPoints: Set<Point>,
    val fee: Money
)

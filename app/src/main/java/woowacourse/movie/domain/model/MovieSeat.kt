package woowacourse.movie.domain.model

data class MovieSeat(
    val row: Int,
    val column: Int,
) {
    val seatType: SeatType = SeatType.from(row)
}

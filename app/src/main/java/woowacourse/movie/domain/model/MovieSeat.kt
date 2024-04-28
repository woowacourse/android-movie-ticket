package woowacourse.movie.domain.model

data class MovieSeat(
    val seatRow: String,
    val seatColumn: Int,
    val seatType: SeatType,
)

package woowacourse.movie.domain.model.cinema.seat

data class Seat(
    val row: Int,
    val col: Int,
    val type: SeatType,
)

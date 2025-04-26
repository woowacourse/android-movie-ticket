package woowacourse.movie.domain.model.cinema.screen

data class Seat(
    val row: Int,
    val col: Int,
    val type: SeatType,
)

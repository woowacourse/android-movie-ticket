package woowacourse.movie.model.board

import woowacourse.movie.model.Price

data class Seat(
    val position: Position,
    val price: Price,
    val grade: SeatGrade,
    val state: SeatState,
)

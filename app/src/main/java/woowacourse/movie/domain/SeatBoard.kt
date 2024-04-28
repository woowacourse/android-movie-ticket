package woowacourse.movie.domain

import woowacourse.movie.domain.seat.Seat

data class SeatBoard(
    val id: Int,
    val horizontalLength: Int,
    val verticalLength: Int,
    val seats: List<Seat>,
)
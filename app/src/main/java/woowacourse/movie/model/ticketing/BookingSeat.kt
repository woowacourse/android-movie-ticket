package woowacourse.movie.model.ticketing

import woowacourse.movie.model.theater.SeatClass

data class BookingSeat(
    val row: Int,
    val column: Int,
    val seatClass: SeatClass,
)

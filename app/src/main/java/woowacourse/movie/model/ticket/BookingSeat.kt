package woowacourse.movie.model.ticket

import woowacourse.movie.model.SeatClass

data class BookingSeat(
    val row: Int,
    val column: Int,
    val seatClass: SeatClass,
)

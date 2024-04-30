package woowacourse.movie.utils

import woowacourse.movie.model.Seat

fun formatSeat(seat: Seat): String = "${('A'.code + seat.row).toChar()}${seat.col + 1}"

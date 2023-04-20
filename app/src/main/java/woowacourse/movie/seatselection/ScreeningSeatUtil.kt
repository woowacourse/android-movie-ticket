package woowacourse.movie.util

import domain.seat.ScreeningSeat
import domain.seat.SeatColumn
import domain.seat.SeatRow

private const val SEAT_UNIT = 4

fun Int.toScreeningSeat(): ScreeningSeat {
    val row = this / SEAT_UNIT
    val column = this % SEAT_UNIT

    return ScreeningSeat.valueOf(
        SeatRow.valueOf(row),
        SeatColumn.valueOf(column)
    )
}

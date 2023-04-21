package woowacourse.movie.seatselection

import android.graphics.Color
import domain.seat.ScreeningSeat
import domain.seat.SeatColumn
import domain.seat.SeatRate
import domain.seat.SeatRow

private const val SEAT_UNIT = 4
private const val START_ROW = 'A'

fun Int.toScreeningSeat(): ScreeningSeat {
    val row = this / SEAT_UNIT
    val column = this % SEAT_UNIT

    return ScreeningSeat.valueOf(
        SeatRow.valueOf(row),
        SeatColumn.valueOf(column)
    )
}

fun SeatRate.toColor() = when (this) {
    SeatRate.B -> Color.MAGENTA
    SeatRate.A -> Color.BLUE
    SeatRate.S -> Color.GREEN
}

fun getSeatText(rowPosition: Int, colPosition: Int, startRow: Char = START_ROW): String {
    return (startRow.code + rowPosition).toChar() + colPosition.toString()
}

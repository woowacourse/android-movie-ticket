package woowacourse.movie.uimodel

import movie.seat.SeatColumn
import movie.seat.SeatRow

data class SeatUi(
    val row: SeatRow,
    val col: SeatColumn,
) {

    fun getSeatPosition(): String {
        return getSeatRow() + getSeatColumn()
    }

    private fun getSeatRow(): String {
        return when (row) {
            SeatRow.A -> "A"
            SeatRow.B -> "B"
            SeatRow.C -> "C"
            SeatRow.D -> "D"
            SeatRow.E -> "E"
        }
    }

    private fun getSeatColumn(): String {
        return when (col) {
            SeatColumn.ONE -> "1"
            SeatColumn.TWO -> "2"
            SeatColumn.THREE -> "3"
            SeatColumn.FOUR -> "4"
        }
    }
}

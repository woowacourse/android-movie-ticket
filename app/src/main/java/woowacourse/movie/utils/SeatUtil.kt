package woowacourse.movie.utils

import movie.seat.Seat
import movie.seat.SeatColumn
import movie.seat.SeatRow

object SeatUtil {

    fun getSeatPosition(seat: Seat): String {
        return getSeatRow(seat) + getSeatColumn(seat)
    }

    private fun getSeatRow(seat: Seat): String {
        return when (seat.row) {
            SeatRow.A -> "A"
            SeatRow.B -> "B"
            SeatRow.C -> "C"
            SeatRow.D -> "D"
            SeatRow.E -> "E"
        }
    }

    private fun getSeatColumn(seat: Seat): String {
        return when (seat.col) {
            SeatColumn.ONE -> "1"
            SeatColumn.TWO -> "2"
            SeatColumn.THREE -> "3"
            SeatColumn.FOUR -> "4"
        }
    }
}

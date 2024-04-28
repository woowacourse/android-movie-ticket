package woowacourse.movie.domain.seat

import java.io.Serializable

data class Seat(val row: String, val col: Int, val seatGrade: SeatGrade) : Serializable {
    val price: Int = when(seatGrade) {
        SeatGrade.S -> 15000
        SeatGrade.A -> 12000
        SeatGrade.B -> 10000
    }
    fun samePositionWith(row: String, col: Int) : Boolean =
        this.row == row && this.col == col
}

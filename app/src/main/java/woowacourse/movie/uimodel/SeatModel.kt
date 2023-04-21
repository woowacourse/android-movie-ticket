package woowacourse.movie.uimodel

import seat.SeatType
import java.io.Serializable

data class SeatModel(
    val row: Char,
    val column: Int,
    val seatType: SeatType?
) : Serializable {

    override fun toString(): String {
        return "$row$column"
    }
}

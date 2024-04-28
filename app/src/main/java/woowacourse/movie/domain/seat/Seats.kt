package woowacourse.movie.domain.seat

import java.io.Serializable

class Seats : Serializable {
    private var _seatList = mutableListOf<Seat>()

    val totalPrice: Int
        get() = _seatList.sumOf { it.price }

    val seatList: List<Seat>
        get() = _seatList.toList()

    fun add(seat: Seat) {
        _seatList.add(seat)
    }

    fun delete(row: String, col: Int) {
        if(isExist(row, col)) {
            _seatList.removeIf { seat ->
                seat.samePositionWith(row, col)
            }
        }
    }

    fun delete(seat: Seat) {
        if (isExist(seat.row, seat.col)) {
            delete(seat.row, seat.col)
        }
    }

    private fun isExist(row: String, col: Int): Boolean {
        _seatList.forEach {seat ->
            if(seat.samePositionWith(row, col)) return true
        }
        return false
    }
}

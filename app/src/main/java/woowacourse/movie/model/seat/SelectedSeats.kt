package woowacourse.movie.model.seat

import woowacourse.movie.model.reservation.ReservationAmount
import woowacourse.movie.model.reservation.ReservationCount
import java.io.Serializable

class SelectedSeats(val reservationCount: ReservationCount) : Serializable {
    private val _seats: MutableList<Seat> = mutableListOf()
    val seats: List<Seat>
        get() = _seats.toList()

    fun select(seat: Seat) {
        require(seat !in _seats) { INVALID_SELECT_SEAT_MESSAGE }
        _seats.add(seat)
    }

    fun unselect(seat: Seat) {
        require(seat in _seats) { INVALID_UNSELECT_SEAT_MESSAGE }
        _seats.remove(seat)
    }

    fun isSelectable(): Boolean = _seats.size < reservationCount.count

    fun isConfirm(): Boolean = _seats.size == reservationCount.count

    fun amount(): ReservationAmount {
        return seats.fold(ReservationAmount(0)) { acc, seat ->
            acc + seat.amount()
        }
    }

    fun clear() = _seats.clear()

    operator fun contains(seat: Seat) = _seats.contains(seat)

    companion object {
        private const val INVALID_SELECT_SEAT_MESSAGE = "이미 선택하신 좌석입니다."
        private const val INVALID_UNSELECT_SEAT_MESSAGE = "선택되지 않은 좌석입니다."
    }
}

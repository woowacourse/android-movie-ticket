package woowacourse.movie.model.seat

import woowacourse.movie.model.reservation.ReservationAmount
import woowacourse.movie.model.reservation.ReservationCount
import java.io.Serializable

class SelectedSeats(val reservationCount: ReservationCount) : Serializable {
    private val _seats: MutableList<Seat> = mutableListOf()
    val seats: List<Seat>
        get() = _seats.toList()

    fun add(seat: Seat) {
        _seats.add(seat)
    }

    fun remove(seat: Seat) {
        _seats.remove(seat)
    }

    fun isSelectable(): Boolean = _seats.size < reservationCount.count

    fun isConfirm(): Boolean = _seats.size == reservationCount.count

    fun amount(): ReservationAmount {
        return seats.fold(ReservationAmount(0)) { acc, seat ->
            acc + seat.amount()
        }
    }

    operator fun contains(seat: Seat) = _seats.contains(seat)
}

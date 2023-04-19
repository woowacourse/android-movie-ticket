package domain

import java.time.LocalDateTime

class Seats(seats: List<Seat> = emptyList()) {
    private val _seats = seats.toMutableList()
    val seats: List<Seat>
        get() = _seats

    fun add(seat: Seat) {
        _seats.add(seat)
    }

    fun containsSeat(seat: Seat): Boolean = seats.map { it.position }.contains(seat.position)

    fun remove(seat: Seat) {
        _seats.remove(seat)
    }

    fun caculateSeatPrice(dateTime: LocalDateTime): Int {
        return seats.fold(0) { total, price ->
            total + price.applyPolicyPrice(dateTime)
        }
    }
}

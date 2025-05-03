package woowacourse.movie.domain.model.seat

class Seats(
    val availableSelectCount: Int,
    _reservedSeats: MutableSet<Seat> = mutableSetOf(),
    private val _reservingSeats: MutableSet<Seat> = mutableSetOf(),
) {
    val reservedSeats = _reservedSeats.toSet()
    val reservingSeats get() = _reservingSeats.toSet()

    fun isReservedSeat(seat: Seat): Boolean {
        return reservedSeats.contains(seat) || reservingSeats.contains(seat)
    }

    fun reserve(seat: Seat) {
        _reservingSeats.add(seat)
    }

    fun cancelReserve(seat: Seat) {
        _reservingSeats.remove(seat)
    }

    fun isSeatSelectionComplete(): Boolean {
        return reservingSeats.size == availableSelectCount
    }

    fun totalPrice(): Int {
        return reservingSeats.sumOf { seat -> seat.price() }
    }
}

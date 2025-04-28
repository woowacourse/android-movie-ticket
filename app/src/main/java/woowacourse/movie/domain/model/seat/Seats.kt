package woowacourse.movie.domain.model.seat

class Seats(
    private val _seats: MutableSet<Seat> = mutableSetOf(),
) {
    val reserveSeats: Set<Seat> get() = _seats.toSet()

    fun isReservedSeat(seat: Seat): Boolean {
        return !_seats.contains(seat)
    }

    fun reserve(seat: Seat) {
        _seats.add(seat)
    }

    fun cancelReserve(seat: Seat) {
        _seats.remove(seat)
    }

    fun totalPrice(): Int {
        return _seats.sumOf { seat -> seat.price() }
    }
}

package woowacourse.movie.model

class SelectedSeats(private val reservationCount: ReservationCount) {
    private val _seats: MutableList<Seat> = mutableListOf()
    val seats: List<Seat>
        get() = _seats.toList()

    fun add(seat: Seat) {
        _seats.add(seat)
    }

    fun remove(seat: Seat) {
        _seats.remove(seat)
    }

    fun canSelect(): Boolean = _seats.size < reservationCount.count

    fun isConfirm(): Boolean = _seats.size == reservationCount.count

    operator fun contains(seat: Seat) = _seats.contains(seat)
}

package woowacourse.movie.domain.model.tools.seat

class Seats(seats: Set<Seat> = setOf()) {

    private val _value = seats.toMutableSet()

    val value
        get() = _value.toSortedSet()

    val size
        get() = value.size

    fun addSeat(seat: Seat) {
        _value.add(seat)
    }

    fun removeSeat(seat: Seat) {
        _value.remove(seat)
    }

    fun contains(seat: Seat): Boolean = value.contains(seat)

    override fun equals(other: Any?): Boolean {
        if (other is Seats) {
            return this.value.containsAll(other.value)
        }
        return false
    }
}

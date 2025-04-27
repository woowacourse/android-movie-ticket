package woowacourse.movie.domain

class Seats(val capacity: Int) {
    private val _seats = mutableSetOf<Seat>()
    val seats get() = _seats.toList()

    fun add(seat: Seat) {
        if (!isSelectionFinished()) _seats.add(seat)
    }

    fun remove(seat: Seat) {
        _seats.remove(seat)
    }

    fun totalPrice(): Int = seats.sumOf { seat -> seat.price() }

    fun isSelectionFinished(): Boolean = seats.size == capacity
}

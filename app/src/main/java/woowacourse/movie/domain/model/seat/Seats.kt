package woowacourse.movie.domain.model.seat

class Seats {
    private val _items = mutableSetOf<Seat>()
    val item get() = _items.toSet()

    fun addSeat(newSeat: Seat) = _items.add(newSeat)

    fun removeSeat(newSeat: Seat) = _items.remove(newSeat)

    fun isSelected(newSeat: Seat) = _items.contains(newSeat)
}

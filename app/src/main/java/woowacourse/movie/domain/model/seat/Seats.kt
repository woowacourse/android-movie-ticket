package woowacourse.movie.domain.model.seat

class Seats {
    private val _item = mutableSetOf<Seat>()
    val item get() = _item.toSet()

    fun addSeat(newSeat: Seat) = _item.add(newSeat)

    fun removeSeat(newSeat: Seat) = _item.remove(newSeat)

    fun isSelected(newSeat: Seat) = _item.contains(newSeat)

    fun canSelect(limit: Int) = _item.size < limit
    
    fun bookingPrice() = _item.sumOf { it.seatPrice() }
}

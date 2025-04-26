package woowacourse.movie.domain.model

class Seats {
    private val _seats: MutableSet<Seat> = mutableSetOf()
    val seats get() = _seats.toSet()
    val size get() = _seats.size

    fun contains(seat: Seat) = _seats.contains(seat)

    fun add(seat: Seat) = _seats.add(seat)

    fun remove(seat: Seat) = _seats.remove(seat)

    fun totalPrice(): Int = _seats.sumOf { it.price() }
}

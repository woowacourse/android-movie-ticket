package woowacourse.movie.domain.seat

import java.io.Serializable

class Seats(
    seats: Set<Seat> = emptySet(),
) : Serializable {
    private val _seats = seats.toMutableSet()
    val seats get() = _seats.toSet()

    operator fun plus(seat: Seat): Seats = Seats(_seats + seat)

    operator fun minus(seat: Seat): Seats = Seats(_seats - seat)

    fun size(): Int = _seats.size

    fun contains(seat: Seat): Boolean = _seats.contains(seat)

    fun totalPrice(): Int = _seats.sumOf { it.price }
}

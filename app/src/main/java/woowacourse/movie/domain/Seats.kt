package woowacourse.movie.domain

import java.io.Serializable

class Seats(
    seats: Set<Seat> = emptySet(),
) : Serializable {
    private val _seats = seats.toMutableSet()
    val seats: Set<Seat> get() = _seats

    operator fun plus(seat: Seat): Seats = Seats(_seats + seat)

    operator fun minus(seat: Seat): Seats = Seats(_seats - seat)

    fun contains(seat: Seat): Boolean = _seats.contains(seat)
}

package woowacourse.movie.domain

import java.io.Serializable

class Seats(
    seats: Set<Seat> = emptySet(),
) : Serializable {
    private val seats = seats.toMutableSet()

    operator fun plus(seat: Seat): Seats = Seats(seats + seat)

    operator fun minus(seat: Seat): Seats = Seats(seats - seat)

    fun contains(seat: Seat): Boolean = seats.contains(seat)

    fun isEmpty(): Boolean = seats.isEmpty()
}

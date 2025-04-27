package woowacourse.movie.domain

import java.io.Serializable

class Seats(values: Set<Seat> = setOf()) : Serializable {
    private val _seats: MutableSet<Seat> = values.toMutableSet()
    val seats get() = _seats.toSet()

    fun has(point: Seat): Boolean {
        return _seats.contains(point)
    }

    fun totalPrice(): Int {
        return _seats.sumOf { it.price() }
    }

    operator fun minus(point: Seat) {
        _seats.remove(point)
    }

    operator fun plus(point: Seat) {
        _seats.add(point)
    }
}

package woowacourse.movie.domain.model

data class Seats(
    private val seats: List<Seat>,
) {
    constructor(vararg seat: Seat) : this(seat.toList())

    fun totalPrice(): Int = seats.sumOf { it.price() }

    fun count(): Int = seats.size
}

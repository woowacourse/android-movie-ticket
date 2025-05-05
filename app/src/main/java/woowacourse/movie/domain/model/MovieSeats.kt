package woowacourse.movie.domain.model

class MovieSeats(
    seats: Set<MovieSeat> = emptySet<MovieSeat>(),
) {
    private val _value: MutableSet<MovieSeat> = seats.toMutableSet()
    val value: Set<MovieSeat> get() = _value
    val totalPrice: TicketPrice get() = TicketPrice(value.sumOf { TicketPrice.from(it.seatType).value })

    fun add(seat: MovieSeat) {
        _value.add(seat)
    }

    fun remove(seat: MovieSeat) {
        _value.remove(seat)
    }
}

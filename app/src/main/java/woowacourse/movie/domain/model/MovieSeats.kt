package woowacourse.movie.domain.model

class MovieSeats(
    seats: Set<MovieSeat> = emptySet<MovieSeat>(),
) {
    private val _seats: MutableSet<MovieSeat> = seats.toMutableSet()
    val seats: Set<MovieSeat> get() = _seats
    val totalPrice: TicketPrice get() = TicketPrice(seats.sumOf { TicketPrice.from(it.seatType).value })

    fun add(seat: MovieSeat) {
        _seats.add(seat)
    }

    fun addAll(seats: Set<MovieSeat>) {
        _seats.addAll(seats)
    }

    fun remove(seat: MovieSeat) {
        _seats.remove(seat)
    }
}

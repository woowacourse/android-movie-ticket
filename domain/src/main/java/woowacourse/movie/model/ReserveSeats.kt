package woowacourse.movie.model

class ReserveSeats(val seats: List<Seat>) {
    val totalPrice = Price(seats.sumOf { it.price })
}

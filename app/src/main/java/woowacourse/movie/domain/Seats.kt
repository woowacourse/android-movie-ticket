package woowacourse.movie.domain

class Seats(private val seats: MutableList<Seat>) {
    fun reservationPrice() = seats.sumOf { it.seatPrice() }
}

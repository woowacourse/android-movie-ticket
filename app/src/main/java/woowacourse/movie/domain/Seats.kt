package woowacourse.movie.domain

class Seats(private val seats: MutableList<Seat>) {
    val all get() = seats.toList()

    fun addSeat(seat: Seat) = seats.add(seat)

    fun removeSeat(seat: Seat) = seats.remove(seat)

    fun selectedLimit(limit: Int): Boolean = seats.size >= limit

    fun reservationPrice() = seats.sumOf { it.seatPrice() }
}

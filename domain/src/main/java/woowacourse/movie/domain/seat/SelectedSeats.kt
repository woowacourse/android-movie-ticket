package woowacourse.movie.domain.seat

class SelectedSeats(
    private val seats: Set<Seat> = emptySet()
) {
    fun getAllPrice(): Int {
        return seats.fold(0) { price, seat ->
            price + seat.getPriceByClass()
        }
    }

    fun add(seat: Seat): SelectedSeats = SelectedSeats(seats + seat)

    fun delete(seat: Seat): SelectedSeats = SelectedSeats(seats - seat)

    fun contains(seat: Seat): Boolean = seat in seats
}

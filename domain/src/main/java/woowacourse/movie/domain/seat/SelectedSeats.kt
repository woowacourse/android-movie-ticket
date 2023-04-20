package woowacourse.movie.domain.seat

class SelectedSeats(
    private val seats: Set<Seat> = emptySet()
) {
    fun getAllPrice(): Int {
        return seats.fold(0) { price, seat ->
            price + seat.getPriceByClass()
        }
    }
}

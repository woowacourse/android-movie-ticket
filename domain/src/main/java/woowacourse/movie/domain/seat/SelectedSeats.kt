package woowacourse.movie.domain.seat

import woowacourse.movie.domain.PeopleCount

class SelectedSeats(
    private val count: PeopleCount,
    private val seats: Set<Seat> = emptySet()
) {
    fun getAllPrice(): Int {
        return seats.fold(0) { price, seat ->
            price + seat.getPriceByClass()
        }
    }

    fun add(seat: Seat): SelectedSeats = SelectedSeats(count, seats + seat)

    fun delete(seat: Seat): SelectedSeats = SelectedSeats(count, seats - seat)

    fun contains(seat: Seat): Boolean = seat in seats

    fun isSelectionDone(): Boolean = seats.size == count.count
}

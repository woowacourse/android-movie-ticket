package woowacourse.movie.domain.seat

import woowacourse.movie.domain.PeopleCount
import woowacourse.movie.domain.discount.TicketDiscount
import java.time.LocalDateTime

data class SelectedSeats(
    val seats: Set<Seat> = emptySet()
) {
    fun getAllPrice(time: LocalDateTime): Int {
        if (seats.isEmpty()) return 0

        var price = seats.fold(0) { price, seat ->
            price + seat.getPriceByClass()
        }

        price = TicketDiscount(time, PeopleCount(seats.size)).getDiscountPrice(price)

        return price
    }

    fun add(seat: Seat): SelectedSeats = SelectedSeats(seats + seat)

    fun delete(seat: Seat): SelectedSeats = SelectedSeats(seats - seat)

    fun contains(seat: Seat): Boolean = seat in seats

    fun isSelectionDone(count: Int): Boolean = seats.size == count
}

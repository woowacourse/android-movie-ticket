package woowacourse.movie.domain.model.seat

import woowacourse.movie.domain.model.discount.policy.DiscountPolicy
import woowacourse.movie.domain.model.movie.TicketPrice
import woowacourse.movie.domain.model.ticket.Ticket

typealias DomainPickedSeats = PickedSeats

class PickedSeats(val seats: List<Seat> = emptyList()) {
    fun calculateTotalPrice(vararg discountPolicies: DiscountPolicy): TicketPrice = seats
        .map { seat -> seat.getClass() }
        .fold(TicketPrice(0)) { totalPrice, seatClass ->
            totalPrice + seatClass.ticketPrice.applyDiscountPolicy(*discountPolicies)
        }

    fun canPick(ticket: Ticket): Boolean =
        seats.size < ticket.count

    fun add(newSeat: Seat): PickedSeats =
        PickedSeats(seats + newSeat)

    fun remove(seat: Seat): PickedSeats =
        PickedSeats(seats - seat)

    fun isPicked(seat: Seat): Boolean = seat in seats
}

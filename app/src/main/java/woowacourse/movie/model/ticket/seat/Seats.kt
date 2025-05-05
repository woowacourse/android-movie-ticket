package woowacourse.movie.model.ticket.seat

import woowacourse.movie.model.ticket.TicketPrice
import woowacourse.movie.model.ticket.seat.grade.SeatGradePolicy

class Seats(
    private val seatGradePolicy: SeatGradePolicy,
    initSeats: Collection<Seat> = setOf<Seat>(),
) {
    private val _seats: MutableSet<Seat> = initSeats.toMutableSet()
    val seats: Set<Seat> get() = _seats.toSet()

    val totalTicketPrice: TicketPrice
        get() = TicketPrice.calculateTotalPrice(_seats, seatGradePolicy)

    fun isSelectedSeat(seat: Seat): Boolean = seat in _seats

    fun updateSeats(newSeats: Collection<Seat>) {
        _seats.clear()
        _seats.addAll(newSeats)
    }

    fun toggleSeat(seat: Seat): SeatToggleResult =
        if (seat in _seats) {
            removeSeat(seat)
            SeatToggleResult.Removed(seat)
        } else {
            addSeat(seat)
            SeatToggleResult.Added(seat)
        }

    private fun removeSeat(seat: Seat) {
        _seats.remove(seat)
    }

    private fun addSeat(seat: Seat) {
        _seats.add(seat)
    }

    fun size() = _seats.size
}

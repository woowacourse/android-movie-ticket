package woowacourse.movie.model.ticket.seat

import woowacourse.movie.model.ticket.TicketPrice
import woowacourse.movie.model.ticket.seat.grade.SeatGradePolicy

class Seats(
    private val seatGradePolicy: SeatGradePolicy,
    initSeats: Collection<Seat> = setOf<Seat>(),
) {
    val seats: MutableSet<Seat> = initSeats.toMutableSet()
    val totalTicketPrice: TicketPrice
        get() = TicketPrice.calculateTotalPrice(seats, seatGradePolicy)

    fun isSelectedSeat(seat: Seat): Boolean = seat in seats

    fun updateSeats(newSeats: Collection<Seat>) {
        seats.clear()
        seats.addAll(newSeats)
    }

    fun toggleSeat(seat: Seat): SeatToggleResult =
        if (seat in seats) {
            removeSeat(seat)
            SeatToggleResult.Removed(seat)
        } else {
            addSeat(seat)
            SeatToggleResult.Added(seat)
        }

    private fun removeSeat(seat: Seat) {
        seats.remove(seat)
    }

    private fun addSeat(seat: Seat) {
        seats.add(seat)
    }

    fun size() = seats.size

    fun getSeatCodes(): List<String> = seats.map { it.seatCode }
}

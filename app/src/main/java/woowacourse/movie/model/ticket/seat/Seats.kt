package woowacourse.movie.model.ticket.seat

import woowacourse.movie.model.ticket.TicketPrice
import woowacourse.movie.model.ticket.seat.grade.SeatGradePolicy

class Seats(
    initSeats: Collection<Seat> = setOf<Seat>(),
    private val seatGradePolicy: SeatGradePolicy,
) {
    val selectedSeats: MutableSet<Seat> = initSeats.toMutableSet()
    val totalTicketPrice: TicketPrice
        get() = TicketPrice.calculateTotalPrice(selectedSeats, seatGradePolicy)

    fun isSelectedSeat(seat: Seat): Boolean = seat in selectedSeats

    fun toggleSeat(seat: Seat): SeatToggleResult =
        if (seat in selectedSeats) {
            removeSeat(seat)
            SeatToggleResult.Removed(seat)
        } else {
            addSeat(seat)
            SeatToggleResult.Added(seat)
        }

    private fun removeSeat(seat: Seat) {
        selectedSeats.remove(seat)
    }

    private fun addSeat(seat: Seat) {
        selectedSeats.add(seat)
    }

    fun size() = selectedSeats.size

    fun getSeatCodes(): List<String> = selectedSeats.map { it.seatCode }
}

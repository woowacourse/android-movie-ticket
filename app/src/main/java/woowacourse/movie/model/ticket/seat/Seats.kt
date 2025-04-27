package woowacourse.movie.model.ticket.seat

sealed class SeatToggleResult {
    data class Added(
        val seat: Seat,
    ) : SeatToggleResult()

    data class Removed(
        val seat: Seat,
    ) : SeatToggleResult()
}

class Seats(
    initSeats: Collection<Seat> = setOf<Seat>(),
) {
    val selectedSeats: MutableSet<Seat> = initSeats.toMutableSet()

    fun toggleSeat(seat: Seat): SeatToggleResult =
        if (seat in selectedSeats) {
            removeSeat(seat)
            SeatToggleResult.Removed(seat)
        } else {
            addSeat(seat)
            SeatToggleResult.Added(seat)
        }

    private fun addSeat(seat: Seat) {
        selectedSeats.add(seat)
    }

    private fun removeSeat(seat: Seat) {
        selectedSeats.remove(seat)
    }

    fun getSeatsString(): String {
        val seatCodes = selectedSeats.map { it.seatCode }
        return seatCodes.sorted().joinToString()
    }
}

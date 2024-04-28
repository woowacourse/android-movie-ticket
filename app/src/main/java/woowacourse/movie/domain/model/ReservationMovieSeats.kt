package woowacourse.movie.domain.model

import woowacourse.movie.presentation.seat.model.SeatSelectType

class ReservationMovieSeats(
    private val ticketCount: Int,
) {
    var userSeats = arrayListOf<MovieSeat>()
        private set
    var seatSelectType = SeatSelectType.ADD
        private set

    fun getSeatPrice(): Int {
        return userSeats.sumOf { it.seatType.price }
    }

    fun setSeatSelectType(newSeat: MovieSeat) {
        seatSelectType =
            if (isExitsSeat(newSeat)) {
                SeatSelectType.REMOVE
            } else {
                if (isValidTicketCounter()) {
                    SeatSelectType.ADD
                } else {
                    SeatSelectType.PREVENT
                }
            }
    }

    fun addSeat(newSeat: MovieSeat) {
        userSeats.add(newSeat)
    }

    fun deleteSeat(movieSeat: MovieSeat) {
        userSeats.remove(movieSeat)
    }

    fun updateSeatSelectType() {
        seatSelectType =
            if (isPreventType()) {
                SeatSelectType.PREVENT
            } else {
                SeatSelectType.ADD
            }
    }

    private fun isValidTicketCounter(): Boolean {
        return ticketCount > userSeats.size
    }

    private fun isExitsSeat(newSeat: MovieSeat): Boolean {
        return userSeats.contains(newSeat)
    }

    private fun isPreventType(): Boolean {
        return ticketCount == userSeats.size
    }
}

package woowacourse.movie.domain.model

class ReservationMovieSeats(
    private val ticketCount: Int,
) {
    var userSeats = arrayListOf<MovieSeat>()
        private set
    var seatSelectState = SeatSelectState.ADD
        private set

    fun getTotalSeatPrice(): Int {
        return userSeats.sumOf { it.seatType.price }
    }

    fun setSeatSelectType(newSeat: MovieSeat) {
        seatSelectState =
            if (isExitsSeat(newSeat)) {
                SeatSelectState.REMOVE
            } else {
                if (isValidTicketCounter()) {
                    SeatSelectState.ADD
                } else {
                    SeatSelectState.PREVENT
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
        seatSelectState =
            if (isPreventType()) {
                SeatSelectState.PREVENT
            } else {
                SeatSelectState.ADD
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

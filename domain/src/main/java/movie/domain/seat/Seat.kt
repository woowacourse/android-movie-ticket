package movie.domain.seat

data class Seat(private val index: Int) {
    val seatRank = SeatRank.valueOf(index / 8)
}

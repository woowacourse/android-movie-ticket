package movie.domain.seat

data class Seat(val index: Int) {
    val rank = SeatRank.valueOf(index / 8)
}

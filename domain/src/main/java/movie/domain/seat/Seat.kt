package movie.domain.seat

data class Seat(private val index: Int) {
    val rank = SeatRank.valueOf(index / 8)

    override fun toString(): String {
        return RowSeat.valueOf(index / 4).toString() + (index % 4).toString()
    }
}

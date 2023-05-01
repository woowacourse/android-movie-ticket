package movie.domain.seat

data class Seat(val row: Int, val col: Int) {
    val rank = SeatRank.valueOf(row / ROW_DIVIDER )

    companion object {
        private const val ROW_DIVIDER = 2
    }
}

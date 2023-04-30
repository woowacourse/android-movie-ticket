package movie.domain.seat

data class Seat(val index: Int) {
    val rank = SeatRank.valueOf(index / RANK_DIVIDER_STANDARD)

    companion object {
        private const val RANK_DIVIDER_STANDARD = 8
    }
}

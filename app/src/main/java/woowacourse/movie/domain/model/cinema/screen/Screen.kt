package woowacourse.movie.domain.model.cinema.screen

class Screen(
    size: ScreenSize,
) {
    val seats: List<Seat> =
        (STARTING_INDEX until size.rowSize).flatMap { rowIndex ->
            val seatType = calculateSeatType(rowIndex)
            (STARTING_INDEX until size.colSize).map { colIndex ->
                Seat(rowIndex, colIndex, seatType)
            }
        }

    private fun calculateSeatType(row: Int): SeatType =
        when (row) {
            in 0..1 -> SeatType.B_CLASS
            in 2..3 -> SeatType.S_CLASS
            else -> SeatType.A_CLASS
        }

    companion object {
        val DEFAULT_SCREEN = Screen(ScreenSize(5, 4))

        private const val STARTING_INDEX = 0
    }
}

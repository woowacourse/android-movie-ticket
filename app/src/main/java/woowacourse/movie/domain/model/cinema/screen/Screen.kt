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
            in B_CLASS_ROW_RANGE -> SeatType.B_CLASS
            in S_CLASS_ROW_RANGE -> SeatType.S_CLASS
            else -> SeatType.A_CLASS
        }

    companion object {
        val DEFAULT_SCREEN = Screen(ScreenSize(5, 4))

        private const val STARTING_INDEX = 0
        private val B_CLASS_ROW_RANGE = 0..1
        private val S_CLASS_ROW_RANGE = 2..3
    }
}

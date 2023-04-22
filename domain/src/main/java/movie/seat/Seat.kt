package movie.seat

data class Seat(
    val row: SeatRow,
    val col: SeatColumn,
) {

    private fun getSeatTier(): SeatTier {
        return when (row) {
            SeatRow.A, SeatRow.B -> SeatTier.B
            SeatRow.C, SeatRow.D -> SeatTier.S
            SeatRow.E -> SeatTier.A
        }
    }

    fun getSeatPrice(): Int {
        return getSeatTier().price
    }

    companion object {
        private const val ROW_NORMALIZATION = 65
        private const val COLUMN_NORMALIZATION = 48

        fun of(position: String): Seat {
            return Seat(
                SeatRow.of(position[0].code - ROW_NORMALIZATION),
                SeatColumn.of(position[1].code - COLUMN_NORMALIZATION),
            )
        }
    }
}

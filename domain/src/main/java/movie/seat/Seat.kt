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
        return when (getSeatTier()) {
            SeatTier.B -> SeatTier.B.price
            SeatTier.A -> SeatTier.A.price
            SeatTier.S -> SeatTier.S.price
        }
    }

    companion object {
        fun of(position: String): Seat {
            return Seat(
                SeatRow.of(position[0].code - 65),
                SeatColumn.of(position[1].code - 48),
            )
        }
    }
}

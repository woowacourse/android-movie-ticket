package movie.seat

data class Seat(
    private val row: SeatRow,
    private val col: SeatColumn,
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
}

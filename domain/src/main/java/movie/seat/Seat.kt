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

    private fun getSeatRow(): String {
        return when (row) {
            SeatRow.A -> "A"
            SeatRow.B -> "B"
            SeatRow.C -> "C"
            SeatRow.D -> "D"
            SeatRow.E -> "E"
        }
    }

    private fun getSeatColumn(): String {
        return when (col) {
            SeatColumn.ONE -> "0"
            SeatColumn.TWO -> "1"
            SeatColumn.THREE -> "2"
            SeatColumn.FOUR -> "3"
        }
    }

    fun getSeatPosition(): String {
        return getSeatRow() + getSeatColumn()
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

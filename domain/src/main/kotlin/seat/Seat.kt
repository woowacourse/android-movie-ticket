package seat

data class Seat(
    val row: Char,
    val column: Int,
    val seatType: SeatType?
) {
    companion object {
        const val MIN_ROW = 'A'
        const val MAX_ROW = 'E'
        const val MIN_COLUMN = 1
        const val MAX_COLUMN = 4

        fun getSeatType(row: Char): SeatType? =
            enumValues<SeatType>().find { seatType ->
                seatType.targetRows.contains(row)
            }

        fun from(row: Char, column: Int): Seat = Seat(row, column, getSeatType(row))
    }
}

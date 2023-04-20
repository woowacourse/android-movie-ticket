package domain.seat

data class ScreeningSeat(
    val row: SeatRow,
    val column: SeatColumn,
    val rate: SeatRate
) {

    val paymentAmount = rate.getPaymentAmount()
    companion object {

        fun valueOf(row: SeatRow, column: SeatColumn): ScreeningSeat {
            return when (row) {
                SeatRow.A, SeatRow.B -> ScreeningSeat(row, column, SeatRate.B)
                SeatRow.C, SeatRow.D -> ScreeningSeat(row, column, SeatRate.S)
                SeatRow.E -> ScreeningSeat(row, column, SeatRate.A)
            }
        }
    }
}

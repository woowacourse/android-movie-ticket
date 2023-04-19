package domain.seat

import domain.payment.PaymentAmount

data class ScreeningSeat(
    val row: SeatRow,
    val column: SeatColumn,
    val isSelected: Boolean = false
) {

    val payment = when (row) {
        SeatRow.A, SeatRow.B -> PaymentAmount(B_RATE_PAYMENT_AMOUNT)
        SeatRow.C, SeatRow.D -> PaymentAmount(S_RATE_PAYMENT_AMOUNT)
        SeatRow.E -> PaymentAmount(A_RATE_PAYMENT_AMOUNT)
    }

    fun selected(): ScreeningSeat {
        return ScreeningSeat(row, column, true)
    }

    // todo: testcode
    fun canceled(): ScreeningSeat {
        return ScreeningSeat(row, column, false)
    }

    companion object {
        private const val B_RATE_PAYMENT_AMOUNT = 10_000
        private const val A_RATE_PAYMENT_AMOUNT = 12_000
        private const val S_RATE_PAYMENT_AMOUNT = 15_000
    }
}

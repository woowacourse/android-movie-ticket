package domain.seat

import domain.payment.PaymentAmount

class ScreeningSeats {

    private val _values: MutableList<ScreeningSeat> = SeatRow
        .values()
        .flatMap { row ->
            SeatColumn.values().map { column ->
                ScreeningSeat(row, column)
            }
        }.toMutableList()
    val values: List<ScreeningSeat>
        get() = _values.toList()
    val selectedSeatsCount: Int
        get() = _values.count { it.isSelected }
    val selectedSeats: List<ScreeningSeat>
        get() = _values.filter { it.isSelected }

    fun selectSeats(seat: ScreeningSeat) {
        val selectedSeat = seat.selected()

        _values.remove(seat)
        _values.add(selectedSeat)
    }

    fun cancelSeats(seat: ScreeningSeat) {
        val canceledSeat = seat.canceled()

        _values.remove(seat)
        _values.add(canceledSeat)
    }

    fun getTotalPaymentAmount(): PaymentAmount {
        val totalPayment = _values
            .filter { it.isSelected }
            .sumOf { it.payment.value }

        return PaymentAmount(totalPayment)
    }
}

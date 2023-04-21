package domain.reservation

import domain.movie.MovieName
import domain.payment.PaymentAmount
import domain.seat.ScreeningSeat
import domain.seat.ScreeningSeats
import domain.seat.SeatState
import java.time.LocalDateTime

data class SeatSelection(
    val movieName: MovieName,
    val screeningDateTime: LocalDateTime,
    val seatCount: TicketCount,
    val screeningSeats: ScreeningSeats = ScreeningSeats()
) {

    private val _selectedSeats = mutableListOf<ScreeningSeat>()
    val selectedSeats: List<ScreeningSeat>
        get() = _selectedSeats.toList()
    val isCompleted: Boolean
        get() = isCompletedSelectedSeatCount()

    fun selectSeat(seat: ScreeningSeat) {
        if (isCompletedSelectedSeatCount()) {
            throw IllegalStateException(ERROR_OVER_TICKET_COUNT)
        }
        val selectedSeat = screeningSeats.selectSeat(seat)

        selectedSeat?.let {
            _selectedSeats.add(it)
        }
    }

    fun cancelSeat(seat: ScreeningSeat) {
        val selectedSeat = screeningSeats.cancelSeat(seat)

        selectedSeat?.let {
            _selectedSeats.remove(it)
        }
    }

    fun selectingComplete(): List<ScreeningSeat> {
        if (isCompletedSelectedSeatCount()) {
            return _selectedSeats.toList()
        }
        throw IllegalStateException(ERROR_TICKET_COUNT)
    }

    fun getTotalPaymentAmount(): PaymentAmount {
        val totalPayment = _selectedSeats.sumOf { it.paymentAmount.value }

        return PaymentAmount(totalPayment)
    }

    private fun isCompletedSelectedSeatCount() = _selectedSeats.size == seatCount.value

    operator fun get(seat: ScreeningSeat): SeatState {
        return screeningSeats.values[seat]
            ?: throw IllegalArgumentException(ERROR_NOT_EXIST_SEAT)
    }

    companion object {
        private const val ERROR_NOT_EXIST_SEAT = "[ERROR] 사용할 수 없는 좌석입니다."
        private const val ERROR_OVER_TICKET_COUNT = "[ERROR]: 티켓 개수보다 많은 좌석을 선택할 수 없습니다."
        private const val ERROR_TICKET_COUNT = "[ERROR]: 티켓 개수만큼 좌석을 선택해야합니다."
    }
}

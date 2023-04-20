package domain.reservation

import domain.movie.MovieName
import domain.payment.PaymentAmount
import domain.seat.ScreeningSeat
import domain.seat.ScreeningSeats
import java.time.LocalDateTime

data class SeatReservation(
    val movieName: MovieName,
    val screeningTime: LocalDateTime,
    val selectingCount: TicketCount,
    val screeningSeats: ScreeningSeats = ScreeningSeats()
) {

    private val _selectedSeats = mutableListOf<ScreeningSeat>()
    val selectedSeats: List<ScreeningSeat>
        get() = _selectedSeats.toList()

    fun selectSeat(seat: ScreeningSeat) {
        if (_selectedSeats.size == selectingCount.value) {
            throw IllegalStateException(ERROR_TICKET_COUNT)
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
        if (selectingCount.value == _selectedSeats.size) {
            return _selectedSeats.toList()
        }
        throw IllegalStateException(ERROR_TICKET_COUNT)
    }

    fun getTotalPaymentAmount(): PaymentAmount {
        val totalPayment = _selectedSeats.sumOf { it.payment.value }

        return PaymentAmount(totalPayment)
    }

    companion object {
        private const val ERROR_TICKET_COUNT = "[ERROR]: 티켓 개수만큼 좌석을 선택해야합니다."
    }
}

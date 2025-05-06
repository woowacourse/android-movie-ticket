package woowacourse.movie.view.reservation.seat

import woowacourse.movie.domain.model.ReservationInfo
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.Theater
import woowacourse.movie.domain.model.TicketMachine

class SeatSelectionPresenter(
    private val view: SeatSelectionContract.View,
) : SeatSelectionContract.Presenter {
    private var reservationInfo: ReservationInfo? = null

    private val theater = Theater.default()
    private val ticketMachine = TicketMachine()

    override fun loadSeats(reservationInfo: ReservationInfo?) {
        val seats = theater.createSeats()
        this.reservationInfo = reservationInfo

        view.showSeats(seats)
        updateScreen()
    }

    override fun selectSeat(seat: Seat) {
        try {
            reservationInfo?.updateSeats(seat)
            view.updateSeatSelection(seat, seat.isSelected)
            updateScreen()
        } catch (e: IllegalArgumentException) {
            view.showError(e.message ?: "좌석 선택 오류")
        }
    }

    private fun updateScreen() {
        view.showTotalPrice(reservationInfo?.totalPrice() ?: throw IllegalArgumentException())
        view.enableConfirmButton(canCompleteReservation())
    }

    private fun canCompleteReservation(): Boolean = reservationInfo?.let { ticketMachine.canPublish(it) } ?: false

    override fun showConfirmButton() {
        if (canCompleteReservation()) {
            view.showReservationDialog()
        } else {
            view.showError("좌석을 선택해주세요")
        }
    }

    override fun completeReservation() {
        reservationInfo?.let {
            ticketMachine.publishTickets(it)
            view.navigateToResult(it)
        }
    }
}

package woowacourse.movie.view.reservation.seat

import woowacourse.movie.domain.model.ReservationInfo
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.Theater
import woowacourse.movie.domain.model.TicketMachine

interface SeatSelectionContract {
    interface View {
        fun showSeats(seats: List<Seat>)

        fun updateSeatSelection(
            seat: Seat,
            isSelected: Boolean,
        )

        fun showTotalPrice(price: Int)

        fun showMovieTitle(title: String)

        fun showSelectedSeatsCount(
            selected: Int,
            total: Int,
        )

        fun enableConfirmButton(enabled: Boolean)

        fun showError(message: String)

        fun showReservationDialog()

        fun navigateToResult(reservationInfo: ReservationInfo)
    }

    interface Presenter {
        fun loadSeats()

        fun selectSeat(seat: Seat)

        fun showConfirmButton()

        fun completeReservation()
    }
}

class SeatSelectionPresenter(
    private val view: SeatSelectionContract.View,
    private val reservationInfo: ReservationInfo,
) : SeatSelectionContract.Presenter {
    private val theater = Theater.default()
    private val ticketMachine = TicketMachine()

    override fun loadSeats() {
        val seats = theater.createSeats()
        view.showSeats(seats)
        updateScreen()
    }

    override fun selectSeat(seat: Seat) {
        try {
            reservationInfo.updateSeats(seat)
            view.updateSeatSelection(seat, seat.isSelected)
            updateScreen()
        } catch (e: IllegalArgumentException) {
            view.showError(e.message ?: "좌석 선택 오류")
        }
    }

    private fun updateScreen() {
        view.showTotalPrice(reservationInfo.totalPrice())
        view.enableConfirmButton(canCompleteReservation())
    }

    private fun canCompleteReservation(): Boolean = ticketMachine.canPublish(reservationInfo)

    override fun showConfirmButton() {
        if (canCompleteReservation()) {
            view.showReservationDialog()
        } else {
            view.showError("좌석을 선택해주세요")
        }
    }

    override fun completeReservation() {
        ticketMachine.publishTickets(reservationInfo)
        view.navigateToResult(reservationInfo)
    }
}

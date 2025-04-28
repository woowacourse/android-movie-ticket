package woowacourse.movie.view.seat

import woowacourse.movie.view.reservation.model.TicketUiModel
import woowacourse.movie.view.reservation.model.toDomain
import woowacourse.movie.view.seat.model.SeatUiModel
import woowacourse.movie.view.seat.model.toDomain

class SeatSelectPresenter(
    private val view: SeatSelectContract.View,
    ticket: TicketUiModel,
) : SeatSelectContract.Presenter {
    private var ticket: TicketUiModel = ticket.copy()

    override fun loadSeatSelectScreen() {
        view.showMovieInfo(ticket.movie)
        view.showTotalPrice(ticket.toDomain().totalPrice())
    }

    override fun onClickSeat(seat: SeatUiModel) {
        toggleSeat(seat)
        val ticketDomain = ticket.toDomain()
        val isSelected = ticketDomain.contains(seat.toDomain())
        view.updateSeatSelectionState(seat, isSelected)
        view.updateConfirmButtonState(ticketDomain.isSeatsAllSelected())
        view.showTotalPrice(ticketDomain.totalPrice())
    }

    override fun onClickConfirmButton() {
        view.showConfirmAlertDialog()
    }

    override fun completeReservation() {
        view.navigateToCompleteScreen(ticket)
    }

    private fun toggleSeat(seat: SeatUiModel) {
        val seatDomain = seat.toDomain()
        ticket =
            if (ticket.toDomain().contains(seatDomain)) {
                ticket.copy(seats = ticket.seats - seatDomain)
            } else {
                ticket.copy(seats = ticket.seats + seatDomain)
            }
    }
}

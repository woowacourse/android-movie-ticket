package woowacourse.movie.view.seat

import woowacourse.movie.view.reservation.model.TicketUiModel
import woowacourse.movie.view.reservation.model.toDomain
import woowacourse.movie.view.seat.model.SeatUiModel
import woowacourse.movie.view.seat.model.toDomain
import woowacourse.movie.view.seat.model.toUiModel

class SeatSelectPresenter(
    private val view: SeatSelectContract.View,
    ticket: TicketUiModel,
) : SeatSelectContract.Presenter {
    private var _ticket: TicketUiModel = ticket.copy()
    val ticket get() = _ticket

    override fun loadSeatSelectScreen() {
        view.showMovieInfo(ticket.movie)
        view.showTotalPrice(ticket.totalPrice)
        view.updateConfirmButtonState(ticket.toDomain().isSeatsAllSelected())
        ticket.seats.seats.forEach {
            view.updateSeatSelectionState(it.toUiModel(), true)
        }
    }

    override fun restoreTicket(ticket: TicketUiModel) {
        _ticket = ticket
        loadSeatSelectScreen()
    }

    override fun onClickSeat(seat: SeatUiModel) {
        toggleSeat(seat)
        val ticketDomain = ticket.toDomain()
        val isSelected = ticketDomain.contains(seat.toDomain())
        view.updateSeatSelectionState(seat, isSelected)
        view.updateConfirmButtonState(ticketDomain.isSeatsAllSelected())
        view.showTotalPrice(ticket.totalPrice)
    }

    override fun onClickConfirmButton() {
        view.showConfirmAlertDialog()
    }

    override fun completeReservation() {
        view.navigateToCompleteScreen(ticket)
    }

    private fun toggleSeat(seat: SeatUiModel) {
        val seatDomain = seat.toDomain()
        _ticket =
            if (ticket.toDomain().contains(seatDomain)) {
                ticket.copy(seats = ticket.seats - seatDomain)
            } else {
                ticket.copy(seats = ticket.seats + seatDomain)
            }
    }
}

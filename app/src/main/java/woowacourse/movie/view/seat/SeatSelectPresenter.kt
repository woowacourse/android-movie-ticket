package woowacourse.movie.view.seat

import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.view.reservation.model.TicketUiModel
import woowacourse.movie.view.reservation.model.toDomain
import woowacourse.movie.view.seat.model.SeatUiModel
import woowacourse.movie.view.seat.model.toDomain

class SeatSelectPresenter(
    private val view: SeatSelectContract.View,
    private var _ticket: TicketUiModel,
) : SeatSelectContract.Presenter {
    private val ticket get() = _ticket

    override fun loadSeatSelectScreen() {
        view.showMovieInfo(ticket.movie)
        view.showTotalPrice(ticket.toDomain().totalPrice())
    }

    override fun onClickSeat(seat: SeatUiModel) {
        val seatDomain = seat.toDomain()
        toggleSeat(seatDomain)
        val ticketDomain = ticket.toDomain()
        view.updateSeatSelection(seat, ticketDomain.contains(seatDomain))
        view.updateConfirmButton(ticketDomain.isSeatsAllSelected())
        view.showTotalPrice(ticketDomain.totalPrice())
    }

    override fun onClickConfirmButton() {
        view.showConfirmAlertDialog()
    }

    override fun completeReservation() {
        view.navigateToCompleteScreen(ticket)
    }

    private fun toggleSeat(seat: Seat) {
        _ticket =
            if (ticket.toDomain().contains(seat)) {
                ticket.copy(seats = ticket.seats - seat)
            } else {
                ticket.copy(seats = ticket.seats + seat)
            }
    }
}

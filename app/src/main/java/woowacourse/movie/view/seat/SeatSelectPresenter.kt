package woowacourse.movie.view.seat

import woowacourse.movie.view.reservation.model.TicketUiModel
import woowacourse.movie.view.reservation.model.toDomain
import woowacourse.movie.view.seat.model.SeatUiModel
import woowacourse.movie.view.seat.model.toDomain

class SeatSelectPresenter(
    private val view: SeatSelectContract.View,
    private var _ticket: TicketUiModel,
) : SeatSelectContract.Presenter {
    val ticket: TicketUiModel
        get() = _ticket

    override fun loadSeatSelectScreen() {
        view.showMovieInfo(ticket.movie)
        view.showTotalPrice(ticket.toDomain().totalPrice())
    }

    override fun onClickSeat(seat: SeatUiModel) {
        val seatDomainModel = seat.toDomain()
        _ticket =
            if (ticket.toDomain().contains(seatDomainModel)) {
                ticket.copy(seats = ticket.seats - seatDomainModel)
            } else {
                ticket.copy(seats = ticket.seats + seatDomainModel)
            }
        view.updateSeatSelection(seat, ticket.toDomain().contains(seatDomainModel))
        view.updateConfirmButton(ticket.toDomain().isSeatsAllSelected())
        view.showTotalPrice(ticket.toDomain().totalPrice())
    }

    override fun onClickConfirmButton() {
        view.showConfirmAlertDialog()
    }

    override fun completeReservation() {
        view.navigateToCompleteScreen(ticket)
    }
}

package woowacourse.movie.view.seat

import woowacourse.movie.domain.Position
import woowacourse.movie.domain.Seat
import woowacourse.movie.view.reservation.model.TicketUiModel
import woowacourse.movie.view.reservation.model.toDomain

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

    override fun onClickSeat(position: Position) {
        val seat = Seat(position)
        _ticket =
            if (ticket.toDomain().contains(seat)) {
                ticket.copy(seats = ticket.seats - seat)
            } else {
                ticket.copy(seats = ticket.seats + seat)
            }
        view.updateSeatSelection(position, ticket.toDomain().contains(seat))
        view.updateConfirmButton(ticket.seats.isEmpty().not())
        view.showTotalPrice(ticket.toDomain().totalPrice())
    }

    override fun onClickConfirmButton() {
        if (ticket.toDomain().isSeatsAllSelected()) {
            view.showConfirmAlertDialog()
        } else {
            view.showSelectToast()
        }
    }

    override fun completeReservation() {
        view.navigateToCompleteScreen(ticket)
    }
}

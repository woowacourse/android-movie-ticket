package woowacourse.movie.presenter.seatSelection

import woowacourse.movie.domain.Seat
import woowacourse.movie.domain.Seats
import woowacourse.movie.domain.Ticket
import woowacourse.movie.view.model.TicketUiModel
import woowacourse.movie.view.model.toDomain
import woowacourse.movie.view.model.toUiModel
import woowacourse.movie.view.seatSelection.SeatSelectionActivity
import woowacourse.movie.view.seatSelection.SeatSelectionActivity.Companion.KEY_TICKET
import woowacourse.movie.view.utils.getParcelableCompat

class SeatSelectionPresenter(
    val view: SeatSelectionActivity,
) : SeatSelectionContract.Presenter {
    private lateinit var _ticket: Ticket
    val ticket get() = _ticket.toUiModel()

    private lateinit var _seats: Seats
    val seats get() = _seats.toUiModel()

    override fun loadReservationInfo() {
        view.intent.extras?.getParcelableCompat<TicketUiModel>(KEY_TICKET)
            ?.let { ticket -> _ticket = ticket.toDomain() }
            ?: return
        _seats = Seats(ticket.count)

        view.showMovieTitle(ticket.movie.title)
    }

    override fun onSeatSelection(
        row: Int,
        col: Int,
    ) {
        val seat = Seat(row, col)
        if (seat in _seats.seats) {
            view.deselectSeat(row, col)
            _seats.remove(seat)
        } else {
            if (!_seats.isSelectionFinished()) {
                view.selectSeat(row, col)
                _seats.add(seat)
            } else {
                view.showToast()
            }
        }

        val price = _seats.totalPrice()
        view.showTotalPrice(price)
    }

    override fun onConfirmation() {
        view.showAlertDialog()
    }

    override fun onAlertConfirmation() {
        view.goToReservationResult(ticket, seats)
    }
}

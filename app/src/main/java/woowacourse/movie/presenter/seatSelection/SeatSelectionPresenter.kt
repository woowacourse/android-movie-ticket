package woowacourse.movie.presenter.seatSelection

import woowacourse.movie.R
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
        _ticket =
            view.intent.extras?.getParcelableCompat<TicketUiModel>(KEY_TICKET)?.toDomain() ?: return
        _seats = Seats(ticket.count)

        view.showMovieTitle(ticket.movie.title)
        view.showTotalPrice(formatterPrice())
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
                view.showSelectionFinishedToast()
            }
        }

        view.showTotalPrice(formatterPrice())
    }

    override fun onConfirmation() {
        if (_seats.isSelectionFinished()) {
            view.showAlertDialog()
        } else {
            val message = view.getString(R.string.need_to_select_more_seats_toast_message).format(_seats.capacity)
            view.showSelectionNotFinishedToast(message)
        }
    }

    override fun onAlertConfirmation() {
        view.goToReservationResult(ticket, seats)
    }

    private fun formatterPrice(): String {
        val priceTemplate = view.getString(R.string.ticket_price_format)
        return priceTemplate.format(_seats.totalPrice())
    }
}

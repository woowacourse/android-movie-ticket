package woowacourse.movie.presenter.seatSelection

import woowacourse.movie.R
import woowacourse.movie.domain.Seat
import woowacourse.movie.domain.Theater
import woowacourse.movie.domain.Ticket
import woowacourse.movie.view.model.TheaterUiModel
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
    private lateinit var _theater: Theater
    val theater get() = _theater.toUiModel()

    override fun loadReservationInfo() {
        _ticket =
            view.intent.extras?.getParcelableCompat<TicketUiModel>(KEY_TICKET)?.toDomain() ?: return
        _theater = Theater(ticket.count)

        view.showMovieTitle(ticket.movie.title)
        view.showTotalPrice(formatterPrice())
    }

    override fun onInstanceStateRestored(seats: TheaterUiModel) {
        _theater = seats.toDomain()
        _theater.seats.forEach { seat ->
            view.selectSeat(seat.row, seat.col)
        }
        view.showTotalPrice(formatterPrice())
    }

    override fun onSeatSelection(
        row: Int,
        col: Int,
    ) {
        val seat = Seat(row, col)
        if (seat in _theater.seats) {
            view.deselectSeat(row, col)
            _theater.remove(seat)
        } else {
            if (!_theater.isSelectionFinished()) {
                view.selectSeat(row, col)
                _theater.add(seat)
            } else {
                view.showSelectionFinishedToast()
            }
        }

        view.showTotalPrice(formatterPrice())
    }

    override fun onConfirmation() {
        if (_theater.isSelectionFinished()) {
            view.showAlertDialog()
        } else {
            val message = view.getString(R.string.need_to_select_more_seats_toast_message).format(theater.capacity)
            view.showSelectionNotFinishedToast(message)
        }
    }

    override fun onAlertConfirmation() {
        view.goToReservationResult(ticket, theater)
    }

    private fun formatterPrice(): String {
        val priceTemplate = view.getString(R.string.ticket_price_format)
        return priceTemplate.format(_theater.totalPrice())
    }
}

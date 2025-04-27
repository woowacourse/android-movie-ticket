package woowacourse.movie.presenter.seatSelection

import woowacourse.movie.R
import woowacourse.movie.domain.movie.Ticket
import woowacourse.movie.domain.theater.Seat
import woowacourse.movie.domain.theater.Theater
import woowacourse.movie.view.model.movie.TicketUiModel
import woowacourse.movie.view.model.theater.TheaterUiModel
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

    override fun onViewCreated() {
        _ticket =
            view.intent.extras?.getParcelableCompat<TicketUiModel>(KEY_TICKET)?.toDomain() ?: return
        _theater = Theater(ticket.count)

        view.showMovieTitle(ticket.movie.title)
        view.showTotalPrice(formattedPrice())
    }

    override fun onInstanceStateRestored(seats: TheaterUiModel) {
        _theater = seats.toDomain()
        _theater.seats.forEach { seat ->
            val index = seat.row * Theater.COL_SIZE + seat.col
            view.selectSeat(index)
        }
        view.showTotalPrice(formattedPrice())
    }

    override fun onSeatSelection(index: Int) {
        val row = index / Theater.COL_SIZE
        val col = index % Theater.COL_SIZE
        val seat = Seat(row, col)
        if (seat in _theater.seats) {
            view.deselectSeat(index)
            _theater.remove(seat)
        } else {
            if (!_theater.isSelectionFinished()) {
                view.selectSeat(index)
                _theater.add(seat)
            } else {
                view.showSelectionFinishedToast()
            }
        }

        view.showTotalPrice(formattedPrice())
    }

    private fun formattedPrice(): String {
        val priceTemplate = view.getString(R.string.template_price)
        return priceTemplate.format(_theater.totalPrice())
    }

    override fun onConfirmation() {
        if (_theater.isSelectionFinished()) {
            view.showAlertDialog()
        } else {
            val message = view.getString(R.string.toast_message_need_to_select_more_seats).format(theater.capacity)
            view.showSelectionNotFinishedToast(message)
        }
    }

    override fun onAlertConfirmation() {
        view.goToReservationResult(ticket, theater)
    }
}

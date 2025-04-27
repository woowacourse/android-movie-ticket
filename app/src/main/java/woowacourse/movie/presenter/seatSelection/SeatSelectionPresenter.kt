package woowacourse.movie.presenter.seatSelection

import woowacourse.movie.domain.movie.Ticket
import woowacourse.movie.domain.theater.Seat
import woowacourse.movie.domain.theater.Theater
import woowacourse.movie.view.model.movie.TicketUiModel
import woowacourse.movie.view.model.theater.TheaterUiModel
import woowacourse.movie.view.model.toDomain
import woowacourse.movie.view.model.toUiModel

class SeatSelectionPresenter(
    val view: SeatSelectionContract.View,
) : SeatSelectionContract.Presenter {
    private lateinit var ticket: Ticket
    private lateinit var _theater: Theater
    val theater get() = _theater.toUiModel()

    override fun onViewCreated(ticketUiModel: TicketUiModel) {
        ticket = ticketUiModel.toDomain()
        _theater = Theater(ticketUiModel.count)
        view.showMovieTitle(ticketUiModel.movie.title)
        view.showTotalPrice(_theater.totalPrice())
    }

    override fun onInstanceStateRestored(seats: TheaterUiModel) {
        _theater = seats.toDomain()
        _theater.seats.forEach { seat ->
            val index = seat.row * Theater.COL_SIZE + seat.col
            view.selectSeat(index)
        }
        view.showTotalPrice(_theater.totalPrice())
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

        view.showTotalPrice(_theater.totalPrice())
    }

    override fun onConfirmation() {
        if (_theater.isSelectionFinished()) {
            view.showAlertDialog()
        } else {
            view.showSelectionNotFinishedToast(ticket.count.value)
        }
    }

    override fun onAlertConfirmation() {
        view.goToReservationResult(ticket.toUiModel(), theater)
    }
}

package woowacourse.movie.feature.seatSelection

import woowacourse.movie.domain.movie.Ticket
import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.domain.seat.Seats
import woowacourse.movie.feature.model.movie.TicketUiModel
import woowacourse.movie.feature.model.seat.SeatsUiModel
import woowacourse.movie.feature.model.toDomain
import woowacourse.movie.feature.model.toUiModel

class SeatSelectionPresenter(
    val view: SeatSelectionContract.View,
) : SeatSelectionContract.Presenter {
    private lateinit var ticket: Ticket
    private lateinit var _seats: Seats
    val seats get() = _seats.toUiModel()

    override fun onViewCreated(ticketUiModel: TicketUiModel) {
        ticket = ticketUiModel.toDomain()
        _seats = Seats(ticketUiModel.count)
        view.showMovieTitle(ticketUiModel.movie.title)
        view.showTotalPrice(_seats.totalPrice())
    }

    override fun onInstanceStateRestored(seats: SeatsUiModel) {
        _seats = seats.toDomain()
        _seats.seats.forEach { seat ->
            val index = seat.row * Seats.COL_SIZE + seat.col
            view.toggleSeat(index, false)
        }
        view.showTotalPrice(_seats.totalPrice())
    }

    override fun onSeatSelection(index: Int) {
        val row = index / Seats.COL_SIZE
        val col = index % Seats.COL_SIZE
        val seat = Seat(row, col)
        if (seat in _seats.seats) {
            view.toggleSeat(index, true)
            _seats.remove(seat)
        } else {
            if (!_seats.isSelectionFinished()) {
                view.toggleSeat(index, false)
                _seats.add(seat)
            } else {
                view.showSelectionFinishedToast()
            }
        }

        view.showTotalPrice(_seats.totalPrice())
    }

    override fun onConfirmation() {
        if (_seats.isSelectionFinished()) {
            view.showAlertDialog()
        } else {
            view.showSelectionNotFinishedToast(ticket.count.value)
        }
    }

    override fun onAlertConfirmation() {
        view.goToReservationResult(ticket.toUiModel(), seats)
    }
}

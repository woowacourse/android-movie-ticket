package woowacourse.movie.presenter.seatSelection

import android.util.Log
import woowacourse.movie.domain.Seat
import woowacourse.movie.domain.Ticket
import woowacourse.movie.view.model.TicketUiModel
import woowacourse.movie.view.model.toUiModel
import woowacourse.movie.view.seatSelection.SeatSelectionActivity
import woowacourse.movie.view.seatSelection.SeatSelectionActivity.Companion.KEY_TICKET
import woowacourse.movie.view.utils.getParcelableCompat

class SeatSelectionPresenter(
    val view: SeatSelectionActivity,
) : SeatSelectionContract.Presenter {
    private lateinit var _ticket: Ticket
    val ticket get() = _ticket.toUiModel()
    private val selectedSeats = mutableSetOf<Seat>()

    override fun loadReservationInfo() {
        val ticket =
            view.intent.extras?.getParcelableCompat<TicketUiModel>(KEY_TICKET)
                ?: run { return }

        view.showMovieTitle(ticket.movie.title)
    }

    override fun onSeatSelection(
        row: Int,
        col: Int,
    ) {
        Log.wtf("asdf", "$row, $col")
        val seat = Seat(row, col)
        if (seat in selectedSeats) {
            view.deselectSeat(row, col)
            selectedSeats.remove(seat)
        } else {
            view.selectSeat(row, col)
            selectedSeats.add(seat)
        }
    }

    override fun onSelection() {
        view.showAlertDialog()
    }

    override fun onSelectionConfirmation() {
        view.confirmSelection(ticket)
    }
}

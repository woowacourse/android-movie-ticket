package woowacourse.movie.activity.seatselection

import android.widget.TextView
import woowacourse.movie.domain.Ticket

class SeatSelectionPresenter : SeatSelectionContract.Presenter {
    private var view: SeatSelectionContract.View? = null
    private var ticketPrice: Int = 0

    override fun attachView(view: SeatSelectionContract.View) {
        this.view = view
    }

    override fun loadMovie(ticket: Ticket) {
        view?.showMovieInfo(ticket)
    }

    override fun calculateMoney(
        rowIndex: Int,
        isSelected: Boolean,
    ) {
        val oneTicketPrice =
            when (rowIndex) {
                0, 1 -> B_CLASS_SEAT_PRICE
                2, 3 -> S_CLASS_SEAT_PRICE
                else -> A_CLASS_SEAT_PRICE
            }

        when (isSelected) {
            true -> ticketPrice += oneTicketPrice
            false -> ticketPrice -= oneTicketPrice
        }

        view?.showMoney(ticketPrice)
    }

    override fun onSeatClicked(seat: TextView): Boolean {
        seat.isSelected = !seat.isSelected
        return seat.isSelected
    }

    override fun handleConfirmButtonActivation(seats: Sequence<Sequence<TextView>>) {
        val hasSelection = seats.flatten().any { it.isSelected }
        view?.updateConfirmButtonState(hasSelection)
    }

    companion object {
        private const val B_CLASS_SEAT_PRICE = 10000
        private const val S_CLASS_SEAT_PRICE = 15000
        private const val A_CLASS_SEAT_PRICE = 12000
    }
}

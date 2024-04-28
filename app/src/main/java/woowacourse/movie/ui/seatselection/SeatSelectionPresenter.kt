package woowacourse.movie.ui.seatselection

import woowacourse.movie.domain.Ticket
import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.domain.seat.Seats

class SeatSelectionPresenter(
    private var view: SeatSelectionContract.View,
) : SeatSelectionContract.Presenter {
    private lateinit var ticket: Ticket
    private lateinit var seats: Seats
    private var title: String = ""

    override fun initialize(
        ticket: Ticket,
        title: String,
    ) {
        this.ticket = ticket
        this.seats = ticket.seats
        this.title = title

        view.initViews(title)
        updatePrice()
    }

    override fun onSeatClicked(seat: Seat) {
        if (seats.seatList.contains(seat)) {
            seats.delete(seat)
        } else {
            if (seats.seatList.size < ticket.count) seats.add(seat)
        }

        view.updateSeatSelection(seat, seats.seatList.contains(seat))
        updatePrice()
        updateConfirmButtonState()
    }

    override fun onConfirmClicked() {
        if (seats.seatList.size == ticket.count) {
            view.navigateToResultActivity(ticket, title)
        }
    }

    override fun isCountSufficient(): Boolean {
        return seats.seatList.size < ticket.count
    }

    private fun updatePrice() {
        view.updatePrice(seats.totalPrice)
    }

    override fun updateConfirmButtonState() {
        view.updateConfirmButton(isCountSufficient())
    }
}

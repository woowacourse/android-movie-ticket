package woowacourse.movie.presenter

import woowacourse.movie.contract.SeatSelectionContract
import woowacourse.movie.domain.Seat
import woowacourse.movie.domain.ticket.Ticket

class SeatSelectionPresenter(
    private val view: SeatSelectionContract.View,
    private val ticket: Ticket,
) : SeatSelectionContract.Presenter {
    private val seats: Set<Seat> = Seat.seats()

    override fun presentSeats() {
        view.setSeats(seats)
    }

    override fun presentTitle() {
        view.setTitle(ticket.title)
    }

    override fun presentPrice(price: Int) {
        view.setPrice(price)
    }
}

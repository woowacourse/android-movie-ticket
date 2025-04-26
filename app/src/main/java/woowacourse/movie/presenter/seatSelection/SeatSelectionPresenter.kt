package woowacourse.movie.presenter.seatSelection

import woowacourse.movie.model.movie.Movie
import woowacourse.movie.model.seat.Seat
import woowacourse.movie.model.seat.SeatGridElement
import woowacourse.movie.model.ticket.TicketCount

class SeatSelectionPresenter(
    private val view: SeatSelectionContracts.View,
) : SeatSelectionContracts.Presenter {
    private lateinit var movie: Movie
    private var ticketCount: TicketCount = TicketCount(1)
    private var seats: MutableList<Seat> = mutableListOf()

    override fun updateReservationInfo(
        movie: Movie,
        ticketCount: TicketCount,
    ) {
        this.movie = movie
        this.ticketCount = ticketCount

        view.showSeats(seats)
        view.showMovieTitle(movie.title)
        view.showPrice(0)
    }

    override fun updateSelectedSeat(
        row: Int,
        column: Int,
    ) {
        val selectedSeat = Seat(SeatGridElement(row), SeatGridElement(column))
        if (seats.contains(selectedSeat)) {
            seats.remove(selectedSeat)
        } else {
            seats.add(selectedSeat)
        }

        updateButtonEnabled()
        updateTotalPrice()
    }

    private fun updateButtonEnabled() {
        val buttonEnabled = seats.size == ticketCount.value
        view.showButtonEnabled(buttonEnabled)
    }

    private fun updateTotalPrice() {
        val totalPrice: Int = seats.sumOf { seat -> seat.price }
        view.showPrice(totalPrice)
    }
}

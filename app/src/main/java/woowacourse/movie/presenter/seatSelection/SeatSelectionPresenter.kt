package woowacourse.movie.presenter.seatSelection

import woowacourse.movie.model.seat.Seat
import woowacourse.movie.model.seat.SeatGridElement
import woowacourse.movie.model.ticket.MovieTicket

class SeatSelectionPresenter(
    private val view: SeatSelectionContracts.View,
) : SeatSelectionContracts.Presenter {
    private lateinit var movieTicket: MovieTicket
    private var seats: MutableList<Seat> = mutableListOf()

    override fun updateTicketData(ticket: MovieTicket) {
        movieTicket = ticket

        view.showSeats(seats)
        view.showMovieTitle(movieTicket.title)
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

        calculateTotalPrice()
    }

    private fun calculateTotalPrice() {
        val totalPrice: Int = seats.sumOf { seat -> seat.price }
        view.showPrice(totalPrice)
    }
}

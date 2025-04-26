package woowacourse.movie.presenter.seatSelection

import woowacourse.movie.model.seat.Seat
import woowacourse.movie.model.ticket.MovieTicket

interface SeatSelectionContracts {
    interface View {
        fun showSeats(seats: List<Seat>)

        fun showMovieTitle(title: String)

        fun showPrice(price: Int)

        fun showButtonEnabled(enabled: Boolean)
    }

    interface Presenter {
        fun updateTicketData(ticket: MovieTicket)

        fun updateSelectedSeat(
            row: Int,
            column: Int,
        )
    }
}

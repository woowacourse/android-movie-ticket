package woowacourse.movie.presenter.seatSelection

import woowacourse.movie.model.movie.Movie
import woowacourse.movie.model.seat.Seat
import woowacourse.movie.model.ticket.TicketCount

interface SeatSelectionContracts {
    interface View {
        fun showSeats(seats: List<Seat>)

        fun showMovieTitle(title: String)

        fun showPrice(price: Int)

        fun showButtonEnabled(enabled: Boolean)
    }

    interface Presenter {
        fun updateReservationInfo(
            movie: Movie,
            ticketCount: TicketCount,
        )

        fun updateSelectedSeat(
            row: Int,
            column: Int,
        )
    }
}

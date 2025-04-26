package woowacourse.movie.presenter.seatSelection

import woowacourse.movie.model.movie.MovieToReserve
import woowacourse.movie.model.seat.Seat
import woowacourse.movie.model.ticket.MovieTicket

interface SeatSelectionContracts {
    interface View {
        fun showSeats(seats: List<Seat>)

        fun showMovieTitle(title: String)

        fun showPrice(price: Int)

        fun showButtonEnabled(enabled: Boolean)

        fun showReservationCompleteView(movieTicket: MovieTicket)
    }

    interface Presenter {
        fun loadSeats()

        fun updateReservationInfo(movieToReserve: MovieToReserve)

        fun updateSelectedSeat(
            row: Int,
            column: Int,
        )

        fun createMovieTicket()
    }
}

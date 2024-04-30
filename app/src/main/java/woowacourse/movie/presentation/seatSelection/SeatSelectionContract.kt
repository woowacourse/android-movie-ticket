package woowacourse.movie.presentation.seatSelection

import woowacourse.movie.model.Movie
import woowacourse.movie.model.Seat
import woowacourse.movie.model.Ticket

interface SeatSelectionContract {
    interface View {
        fun displaySeats(seats: List<Seat>)

        fun displayTicketInfo(movie: Movie)

        fun updateSelectedSeatUI(index: Int)

        fun updateUnSelectedSeatUI(index: Int)

        fun updateTotalPrice(price: Int)

        fun showToastMessage(message: String?)

        fun setButtonEnabledState(isEnabled: Boolean)

        fun navigate(ticket: Ticket)
    }

    interface Presenter {
        fun loadMovieData(id: Int)

        fun loadSeats()

        fun updateSeatSelection(index: Int)

        fun navigate(screeningDateTime: String)
    }
}

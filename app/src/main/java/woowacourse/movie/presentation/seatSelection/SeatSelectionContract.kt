package woowacourse.movie.presentation.seatSelection

import woowacourse.movie.model.Movie
import woowacourse.movie.model.Seat

interface SeatSelectionContract {
    interface View {
        fun initializeSeats(seats: List<Seat>)

        fun initializeTicketInfo(movie: Movie)

        fun updateSelectedSeatUI(index: Int)

        fun updateUnSelectedSeatUI(index: Int)

        fun showToastMessage(message: String?)

        fun setButtonEnabledState(isEnabled: Boolean)
    }

    interface Presenter {
        fun initializeSeats()

        fun initializeTicketInfo()

        fun updateSeatSelection(index: Int)
    }
}

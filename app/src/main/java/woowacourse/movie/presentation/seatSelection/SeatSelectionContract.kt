package woowacourse.movie.presentation.seatSelection

import woowacourse.movie.model.Seat

interface SeatSelectionContract {
    interface View {
        fun initializeSeats(seats: List<Seat>)

        fun updateSelectedSeatUI(index: Int)

        fun updateUnSelectedSeatUI(index: Int)
    }

    interface Presenter {
        fun initializeSeats()

        fun updateSeatSelection(index: Int)
    }
}

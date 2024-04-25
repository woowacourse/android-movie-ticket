package woowacourse.movie.presentation.seatSelection

import woowacourse.movie.model.Seat

interface SeatSelectionContract {
    interface View {
        fun initializeSeats(seats: List<Seat>)
    }

    interface Presenter {
        fun initializeSeats()
    }
}

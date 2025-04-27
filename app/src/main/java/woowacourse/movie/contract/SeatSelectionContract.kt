package woowacourse.movie.contract

import woowacourse.movie.domain.Seat

interface SeatSelectionContract {
    interface Presenter {
        fun presentSeats()

        fun presentTitle()

        fun canSelectSeat(selectedSeatCount: Int): Boolean
    }

    interface View {
        fun setSeats(seats: Set<Seat>)

        fun setTitle(title: String)
    }
}

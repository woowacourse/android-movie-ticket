package woowacourse.movie.presentation.seat

interface SeatSelectionContract {
    interface View {
        fun showSeat()

        fun showSelectedSeat()
    }

    interface Presenter {
        fun loadSeat()

        fun selectSeat()
    }
}

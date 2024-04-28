package woowacourse.movie.seat

interface SeatSelectContract {
    interface View {
        fun showMovieTitle(movieTitle: String)

        fun showTotalPrice()

        fun showReservationCheck()

        fun changeSeatColor(
            isSelected: Boolean,
            onColor: (Int) -> Unit,
        )

        fun moveToReservationFinished()
    }

    interface Presenter {
        fun loadMovieTitle()

        fun selectSeat(onColor: (Int) -> Unit)

        fun unselectSeat(onColor: (Int) -> Unit)
    }
}

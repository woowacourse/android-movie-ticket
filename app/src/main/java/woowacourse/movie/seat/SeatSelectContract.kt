package woowacourse.movie.seat

interface SeatSelectContract {
    interface View {
        fun showMovieTitle(movieTitle: String)

        fun showTotalPrice()

        fun showReservationCheck()

        fun changeSeatColor()

        fun moveToReservationFinished()
    }

    interface Presenter {
        fun loadMovieTitle()

        fun selectSeat()

        fun unselectSeat()
    }
}

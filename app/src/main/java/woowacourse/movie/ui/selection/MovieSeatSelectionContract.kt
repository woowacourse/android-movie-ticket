package woowacourse.movie.ui.selection

import woowacourse.movie.ui.HandleError

interface MovieSeatSelectionContract {
    interface View : HandleError {
        fun showMovieTitle(title: String)

        fun showTheater(
            rowSize: Int,
            colSize: Int,
        )

        fun showSelectedSeat(
            row: Int,
            col: Int,
        )

        fun showUnSelectedSeat(
            row: Int,
            col: Int,
        )

        fun showReservationTotalAmount(amount: Int)

        fun updateSelectCompletion(isComplete: Boolean)

        fun moveMovieReservationCompletePage(ticketId: Long)
    }

    interface Presenter {
        fun loadTheaterInfo(ticketId: Long)

        fun updateSelectCompletion()

        fun selectSeat(
            row: Int,
            col: Int,
        )

        fun reserveMovie(ticketId: Long)
    }
}

package woowacourse.movie.ui.selection

import woowacourse.movie.model.movie.Seat
import woowacourse.movie.ui.HandleError

interface MovieSeatSelectionContract {
    interface View : HandleError {
        fun showMovieTitle(title: String)

        fun showTheater(
            rowSize: Int,
            colSize: Int,
        )

        fun showSelectedSeat(seats: List<Seat>)

        fun showReservationTotalAmount(amount: Int)

        fun updateSelectCompletion(isComplete: Boolean)

        fun moveMovieReservationCompletePage(ticketId: Long)
    }

    interface Presenter {
        fun loadMovieTitle(movieContentId: Long)

        fun loadTotalSeatAmount()

        fun loadTheater()

        fun updateSelectCompletion()

        fun selectSeat(
            row: Int,
            col: Int,
        )

        fun reserveMovie(
            date: String,
            time: String,
        )
    }
}

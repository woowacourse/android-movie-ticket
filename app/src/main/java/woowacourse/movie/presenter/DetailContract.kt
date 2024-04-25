package woowacourse.movie.presenter

import woowacourse.movie.model.Movie
import woowacourse.movie.model.Ticket

interface DetailContract {
    interface Presenter {
        fun loadMovie()
        fun onClickedPlusButton(count: Int)
        fun onClickedMinusCount(count: Int)
        fun onClickedReservation(movie: Movie, count: Int)
    }

    interface View {
        fun showMovie(movie: Movie)
        fun updateCount(count: Int)
        fun showTicket(ticket: Ticket)
    }
}

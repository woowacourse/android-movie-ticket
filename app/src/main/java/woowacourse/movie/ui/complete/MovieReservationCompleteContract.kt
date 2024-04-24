package woowacourse.movie.ui.complete

import woowacourse.movie.model.movie.MovieContent
import woowacourse.movie.model.movie.Ticket
import woowacourse.movie.ui.HandleError

interface MovieReservationCompleteContract {
    interface View : HandleError {
        fun showMovieContent(movieContent: MovieContent)

        fun showTicketUi(ticket: Ticket)
    }

    interface Presenter {
        fun loadMovieContent(movieContentId: Long)

        fun loadTicket(reservationCount: Int)

        fun handleError(throwable: Throwable)
    }
}

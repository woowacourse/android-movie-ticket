package woowacourse.movie.ui.complete

import woowacourse.movie.model.movie.MovieContent
import woowacourse.movie.model.movie.Ticket
import woowacourse.movie.ui.HandleError

interface MovieReservationCompleteContract {
    interface View : HandleError {
        fun showMovieContentUi(movieContent: MovieContent)

        fun updateTicketUi(ticket: Ticket)
    }

    interface Presenter {
        fun loadMovieContent(movieContentId: Long)

        fun updateTicket(reservationCount: Int)

        fun handleError(throwable: Throwable)
    }
}

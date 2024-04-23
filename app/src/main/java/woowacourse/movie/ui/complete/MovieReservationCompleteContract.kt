package woowacourse.movie.ui.complete

import woowacourse.movie.model.movie.MovieContent
import woowacourse.movie.model.movie.Ticket

interface MovieReservationCompleteContract {
    interface View {
        fun showMovieContentUi(movieContent: MovieContent)

        fun updateTicketUi(ticket: Ticket)

        fun showError(e: Exception)
    }

    interface Presenter {
        fun loadMovieContent(movieContentId: Long)

        fun updateTicket(reservationCount: Int)
    }
}

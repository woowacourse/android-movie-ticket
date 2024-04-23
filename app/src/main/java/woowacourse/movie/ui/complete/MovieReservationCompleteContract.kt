package woowacourse.movie.ui.complete

import woowacourse.movie.model.movie.MovieContent
import woowacourse.movie.model.movie.Ticket

interface MovieReservationCompleteContract {
    interface View {
        fun showMovieContentUi(movieContent: MovieContent)

        fun updateTicketUi(ticket: Ticket)
    }

    interface Presenter {
        fun loadMovieContent(movieContentId: Long)

        fun updateTicket(reservationCount: Int)
    }
}

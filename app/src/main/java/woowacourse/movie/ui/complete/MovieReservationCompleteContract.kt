package woowacourse.movie.ui.complete

import woowacourse.movie.model.movie.MovieContent
import woowacourse.movie.model.movie.Ticket
import woowacourse.movie.ui.base.BaseView

interface MovieReservationCompleteContract {
    interface View : BaseView {
        fun showMovieContentUi(movieContent: MovieContent)

        fun updateTicketUi(ticket: Ticket)
    }

    interface Presenter {
        fun loadMovieContent(movieContentId: Long)

        fun updateTicket(reservationCount: Int)
    }
}

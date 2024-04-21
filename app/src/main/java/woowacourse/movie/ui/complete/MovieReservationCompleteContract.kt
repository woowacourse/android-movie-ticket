package woowacourse.movie.ui.complete

import woowacourse.movie.model.MovieContent
import woowacourse.movie.model.Ticket
import woowacourse.movie.ui.base.BaseView

interface MovieReservationCompleteContract {
    interface View : BaseView {
        fun setUpMovieContentUi(movieContent: MovieContent)

        fun setUpTicketUi(ticket: Ticket)
    }

    interface Presenter {
        fun setUpMovieContent(movieContentId: Long)

        fun setUpTicket(reservationCount: Int)
    }
}

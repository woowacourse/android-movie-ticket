package woowacourse.movie.ui.complete

import woowacourse.movie.model.MovieContent
import woowacourse.movie.model.Ticket
import woowacourse.movie.ui.base.BaseView

interface MovieReservationCompleteContract {
    interface View : BaseView {
        fun updateMovieContentUi(movieContent: MovieContent)

        fun updateTicketUi(ticket: Ticket)
    }

    interface Presenter {
        fun updateMovieContent(movieContentId: Long)

        fun updateTicket(reservationCount: Int)
    }
}

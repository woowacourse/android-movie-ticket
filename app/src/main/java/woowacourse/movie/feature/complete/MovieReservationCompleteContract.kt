package woowacourse.movie.feature.complete

import woowacourse.movie.base.BasePresenter
import woowacourse.movie.base.BaseView
import woowacourse.movie.model.Ticket
import woowacourse.movie.model.data.dto.MovieContent

interface MovieReservationCompleteContract {
    interface View : BaseView {
        fun setUpMovieContentUi(movieContent: MovieContent)

        fun setUpTicketUi(ticket: Ticket)
    }

    interface Presenter : BasePresenter {
        fun setUpMovieContent(movieContentId: Long)

        fun setUpTicket(reservationCount: Int)
    }
}

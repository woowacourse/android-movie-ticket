package woowacourse.movie.feature.complete

import woowacourse.movie.model.Ticket
import woowacourse.movie.model.data.dto.MovieContent
import woowacourse.movie.utils.BasePresenter

interface MovieReservationCompleteContract {
    interface View {
        fun setUpMovieContentUi(movieContent: MovieContent)

        fun setUpTicketUi(ticket: Ticket)
    }

    interface Presenter : BasePresenter {
        fun setUpMovieContent(movieContentId: Long)

        fun setUpTicket(reservationCount: Int)
    }
}

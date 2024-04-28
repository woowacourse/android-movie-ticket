package woowacourse.movie.feature.complete

import woowacourse.movie.model.data.dto.Movie
import woowacourse.movie.model.reservation.Ticket
import woowacourse.movie.utils.BasePresenter
import woowacourse.movie.utils.ErrorListener

interface MovieReservationCompleteContract {
    interface View : ErrorListener {
        fun initializeTicket(ticket: Ticket)

        fun initializeReservationCompleteView(movie: Movie)
    }

    interface Presenter : BasePresenter {
        fun loadTicketData(ticketId: Long)

        fun loadMovieData(movieId: Long)
    }
}

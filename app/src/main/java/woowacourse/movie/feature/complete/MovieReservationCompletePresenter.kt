package woowacourse.movie.feature.complete

import woowacourse.movie.model.ReservationCount
import woowacourse.movie.model.Ticket
import woowacourse.movie.model.data.MovieContents

class MovieReservationCompletePresenter(
    private val view: MovieReservationCompleteContract.View,
    private val movieContents: MovieContents,
) : MovieReservationCompleteContract.Presenter {
    override fun setUpMovieContent(movieContentId: Long) {
        val movieContent = movieContents.find(movieContentId)
        view.setUpMovieContentUi(movieContent)
    }

    override fun setUpTicket(reservationCount: Int) {
        val ticket = Ticket(ReservationCount(reservationCount))
        view.setUpTicketUi(ticket)
    }
}

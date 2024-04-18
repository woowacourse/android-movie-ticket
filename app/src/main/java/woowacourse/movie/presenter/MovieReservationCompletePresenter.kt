package woowacourse.movie.presenter

import woowacourse.movie.conrtract.MovieReservationCompleteContract
import woowacourse.movie.dao.MovieContentsImpl
import woowacourse.movie.model.Ticket

class MovieReservationCompletePresenter(private val view: MovieReservationCompleteContract.View) :
    MovieReservationCompleteContract.Presenter {
    override fun setUpMovieContent(movieContentId: Long) {
        val movieContent = MovieContentsImpl.find(movieContentId)
        view.setUpMovieContentUi(movieContent)
    }

    override fun setUpTicket(reservationCount: Int) {
        val ticket = Ticket(reservationCount)
        view.setUpTicketUi(ticket)
    }
}

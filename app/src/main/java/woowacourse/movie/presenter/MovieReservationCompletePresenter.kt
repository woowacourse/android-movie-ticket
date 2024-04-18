package woowacourse.movie.presenter

import woowacourse.movie.conrtract.MovieReservationCompleteContract
import woowacourse.movie.model.ReservationCount
import woowacourse.movie.model.Ticket
import woowacourse.movie.model.data.MovieContentsImpl

class MovieReservationCompletePresenter(private val view: MovieReservationCompleteContract.View) :
    MovieReservationCompleteContract.Presenter {
    override fun setUpMovieContent(movieContentId: Long) {
        val movieContent = MovieContentsImpl.find(movieContentId)
        view.setUpMovieContentUi(movieContent)
    }

    override fun setUpTicket(reservationCount: Int) {
        val ticket = Ticket(ReservationCount(reservationCount))
        view.setUpTicketUi(ticket)
    }
}

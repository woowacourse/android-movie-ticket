package woowacourse.movie.ui.complete

import woowacourse.movie.model.data.MovieContents
import woowacourse.movie.model.movie.ReservationCount
import woowacourse.movie.model.movie.Ticket

class MovieReservationCompletePresenter(
    private val view: MovieReservationCompleteContract.View,
    private val movieContents: MovieContents,
) :
    MovieReservationCompleteContract.Presenter {
    override fun loadMovieContent(movieContentId: Long) {
        try {
            val movieContent = movieContents.find(movieContentId)
            view.showMovieContent(movieContent)
        } catch (e: NoSuchElementException) {
            view.showError(e)
        }
    }

    override fun loadTicket(reservationCount: Int) {
        val ticket = Ticket(ReservationCount(reservationCount))
        view.showTicketUi(ticket)
    }

    override fun handleError(throwable: Throwable) {
        view.showError(throwable)
    }
}

package woowacourse.movie.ui.complete

import woowacourse.movie.model.ReservationCount
import woowacourse.movie.model.Ticket
import woowacourse.movie.model.data.MovieContents

class MovieReservationCompletePresenter(
    private val view: MovieReservationCompleteContract.View,
    private val movieContents: MovieContents,
) :
    MovieReservationCompleteContract.Presenter {
    override fun updateMovieContent(movieContentId: Long) {
        val movieContent = movieContents.find(movieContentId)
        view.updateMovieContentUi(movieContent)
    }

    override fun updateTicket(reservationCount: Int) {
        val ticket = Ticket(ReservationCount(reservationCount))
        view.updateTicketUi(ticket)
    }
}

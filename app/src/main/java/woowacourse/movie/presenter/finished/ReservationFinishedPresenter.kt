package woowacourse.movie.presenter.finished

import woowacourse.movie.db.ScreeningDao
import woowacourse.movie.model.Movie
import woowacourse.movie.model.Ticket

class ReservationFinishedPresenter(
    private val view: ReservationFinishedContract.View,
    private val dao: ScreeningDao,
) : ReservationFinishedContract.Presenter {
    override fun loadMovie(movieId: Int) {
        val movie: Movie = dao.find(movieId)
        view.showMovieInformation(movie)
    }

    override fun loadTicket(ticket: Ticket) {
        val numberOfTickets = ticket.count
        val price = ticket.calculatePrice()

        view.showReservationHistory(numberOfTickets, price)
    }
}

package woowacourse.movie.presenter.finished

import woowacourse.movie.db.screening.ScreeningDao
import woowacourse.movie.model.movie.Movie
import woowacourse.movie.model.seats.Seats
import woowacourse.movie.model.ticket.Ticket

class ReservationFinishedPresenter(
    private val view: ReservationFinishedContract.View,
    private val dao: ScreeningDao,
) : ReservationFinishedContract.Presenter {
    override fun loadMovie(movieId: Int) {
        val movie: Movie = dao.find(movieId)
        view.showMovieInformation(movie)
    }

    override fun loadTicket(
        ticket: Ticket,
        seats: Seats,
    ) {
        view.showReservationHistory(ticket, seats)
    }
}

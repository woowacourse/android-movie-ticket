package woowacourse.movie.presenter.finished

import woowacourse.movie.model.Movie
import woowacourse.movie.model.MovieStorage
import woowacourse.movie.model.Ticket

class ReservationFinishedPresenter(
    private val view: ReservationFinishedContract.View,
) : ReservationFinishedContract.Presenter {
    private val movies = MovieStorage.obtainMovies()

    override fun loadMovie(movieId: Int) {
        val movie: Movie = movies[movieId]
        view.showMovieInformation(movie)
    }

    override fun loadTicket(ticket: Ticket) {
        val numberOfTickets = ticket.count
        val price = ticket.calculatePrice()

        view.showReservationHistory(numberOfTickets, price)
    }
}

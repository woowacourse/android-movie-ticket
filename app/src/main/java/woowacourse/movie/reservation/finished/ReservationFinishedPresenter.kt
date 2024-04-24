package woowacourse.movie.reservation.finished

import woowacourse.movie.db.Movies
import woowacourse.movie.model.Ticket

class ReservationFinishedPresenter(
    private val view: ReservationFinishedContract.View,
    ticketCount: Int,
) : ReservationFinishedContract.Presenter {
    private val movies = Movies.obtainMovies()
    private val ticket = Ticket(ticketCount)

    override fun deliverMovieInformation(movieId: Int) {
        view.showMovieInformation(movies[movieId])
    }

    override fun deliverReservationInformation() {
        val numberOfTickets = ticket.count
        val price = ticket.calculatePrice()

        view.showReservationHistory(numberOfTickets, price)
    }
}

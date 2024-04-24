package woowacourse.movie.reservation.finished

import woowacourse.movie.db.Movies
import woowacourse.movie.model.Ticket

class ReservationFinishedPresenter(
    private val view: ReservationFinishedContract.View,
    private val movieId: Int,
    ticketCount: Int,
) : ReservationFinishedContract.Presenter {
    private val ticket = Ticket(ticketCount)

    init {
        loadMovieInformation()
        loadReservationInformation()
    }

    override fun loadMovieInformation() {
        val movies = Movies.obtainMovie(movieId)

        view.showMovieInformation(movies)
    }

    override fun loadReservationInformation() {
        val numberOfTickets = ticket.count
        val price = ticket.calculatePrice()

        view.showReservationHistory(numberOfTickets, price)
    }
}

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
        deliverMovieInformation()
        deliverReservationInformation()
    }

    override fun deliverMovieInformation() {
        val movies = Movies.obtainMovies()[movieId]

        view.showMovieInformation(movies)
    }

    override fun deliverReservationInformation() {
        val numberOfTickets = ticket.count
        val price = ticket.calculatePrice()

        view.showReservationHistory(numberOfTickets, price)
    }
}

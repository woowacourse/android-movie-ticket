package woowacourse.movie.reservation.finished

import woowacourse.movie.model.Movies
import woowacourse.movie.model.Ticket

class ReservationFinishedPresenter(
    private val contract: ReservationFinishedContract.View,
    ticketCount: Int,
) : ReservationFinishedContract.Presenter {
    private val movies = Movies.obtainMovies()
    private val ticket = Ticket(ticketCount)

    override fun deliverMovieInformation(movieId: Int) {
        contract.showMovieInformation(movies[movieId])
    }

    override fun deliverReservationInformation() {
        val numberOfTickets = ticket.count
        val price = ticket.calculatePrice()

        contract.showReservationHistory(numberOfTickets, price)
    }
}

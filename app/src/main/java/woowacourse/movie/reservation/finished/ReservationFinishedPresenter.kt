package woowacourse.movie.reservation.finished

import woowacourse.movie.model.Movies
import woowacourse.movie.model.Ticket

class ReservationFinishedPresenter(
    private val contract: ReservationFinishedContract,
    ticketCount: Int,
) {
    private val movies = Movies.obtainMovies()
    private val ticket = Ticket(ticketCount)

    fun deliverMovieInformation(movieId: Int) {
        contract.showMovieInformation(movies[movieId])
    }

    fun deliverReservationInformation() {
        val numberOfTickets = ticket.count
        val price = ticket.calculatePrice()

        contract.showReservationHistory(numberOfTickets, price)
    }
}

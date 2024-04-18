package woowacourse.movie.presenter

import woowacourse.movie.model.Movies
import woowacourse.movie.model.Ticket
import woowacourse.movie.view.ReservationFinishedContract

class ReservationFinishedPresenter(private val contract: ReservationFinishedContract, private val ticket: Ticket) {
    private val movies = Movies.obtainMovies()

    fun deliverMovieInformation(movieId: Int) {
        contract.showMovieInformation(movies[movieId])
    }

    fun deliverReservationInformation() {
        val numberOfTickets = ticket.count
        val price = ticket.calculatePrice()

        contract.showReservationHistory(numberOfTickets, price)
    }
}

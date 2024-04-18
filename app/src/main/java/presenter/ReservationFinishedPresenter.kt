package presenter

import domain.Movies
import domain.Ticket
import view.ReservationFinishedContract

class ReservationFinishedPresenter(private val contract: ReservationFinishedContract, private val ticket: Ticket) {
    val movies = Movies.obtainMovies()

    fun deliverMovieInformation(movieId: Int) {
        contract.showMovieInformation(movies[movieId - 1])
    }

    fun deliverReservationInformation() {
        val numberOfTickets = ticket.count
        val price = ticket.calculatePrice()

        contract.showReservationHistory(numberOfTickets, price)
    }
}

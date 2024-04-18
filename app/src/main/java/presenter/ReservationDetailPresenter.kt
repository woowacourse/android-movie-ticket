package presenter

import domain.Failure
import domain.Movies
import domain.Result
import domain.Success
import domain.Ticket
import view.ReservationDetailContract

class ReservationDetailPresenter(private val contract: ReservationDetailContract) {
    val ticket = Ticket()
    private val movies = Movies.obtainMovies()

    fun detectIncreaseCount() {
        contract.initializePlusButton(::increaseTicketCount)
    }

    fun detectDecreaseCount() {
        contract.initializeMinusButton(::decreaseTicketCount)
    }

    private fun increaseTicketCount() {
        val result = ticket.increaseCount()
        handleNumberOfTicketsBounds(result, ticket)
    }

    private fun decreaseTicketCount() {
        val result = ticket.decreaseCount()
        handleNumberOfTicketsBounds(result, ticket)
    }

    fun deliverMovie(movieId: Int) {
        contract.showMovieInformation(movies[movieId - 1])
    }

    fun deliverReservationHistory(movieId: Int) {
        contract.initializeReservationButton(movieId)
    }

    private fun handleNumberOfTicketsBounds(
        result: Result,
        ticket: Ticket,
    ) {
        when (result) {
            is Success -> contract.changeNumberOfTickets(ticket)
            is Failure -> contract.showResultToast()
        }
    }
}

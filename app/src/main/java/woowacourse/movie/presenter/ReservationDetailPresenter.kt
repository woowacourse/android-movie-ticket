package woowacourse.movie.presenter

import woowacourse.movie.model.Failure
import woowacourse.movie.model.Movies
import woowacourse.movie.model.Result
import woowacourse.movie.model.Success
import woowacourse.movie.model.Ticket
import woowacourse.movie.view.ReservationDetailContract

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
        contract.showMovieInformation(movies[movieId])
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

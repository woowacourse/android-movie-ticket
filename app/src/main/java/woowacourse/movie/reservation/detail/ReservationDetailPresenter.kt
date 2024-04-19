package woowacourse.movie.reservation.detail

import woowacourse.movie.model.ChangeTicketCountResult
import woowacourse.movie.model.Failure
import woowacourse.movie.model.Movies
import woowacourse.movie.model.Success
import woowacourse.movie.model.Ticket

class ReservationDetailPresenter(
    private val contract: ReservationDetailContract,
) {
    private val movies = Movies.obtainMovies()
    val ticket = Ticket()

    fun detectIncreaseCount() {
        contract.initializePlusButton(::increaseTicketCount)
    }

    fun detectDecreaseCount() {
        contract.initializeMinusButton(::decreaseTicketCount)
    }

    fun deliverMovie(movieId: Int) {
        contract.showMovieInformation(movies[movieId])
    }

    fun deliverReservationHistory(movieId: Int) {
        contract.initializeReservationButton(movieId)
    }

    private fun increaseTicketCount() {
        val result = ticket.increaseCount()
        handleNumberOfTicketsBounds(result, ticket)
    }

    private fun decreaseTicketCount() {
        val result = ticket.decreaseCount()
        handleNumberOfTicketsBounds(result, ticket)
    }

    private fun handleNumberOfTicketsBounds(
        result: ChangeTicketCountResult,
        ticket: Ticket,
    ) {
        when (result) {
            is Success -> contract.changeNumberOfTickets(ticket)
            is Failure -> contract.showResultToast()
        }
    }
}

package woowacourse.movie.reservation.detail

import woowacourse.movie.model.ChangeTicketCountResult
import woowacourse.movie.model.Failure
import woowacourse.movie.model.Movies
import woowacourse.movie.model.Success
import woowacourse.movie.model.Ticket

class ReservationDetailPresenter(
    private val contract: ReservationDetailContract.View,
    private val movieId: Int,
) : ReservationDetailContract.Presenter {
    private val movies = Movies.obtainMovies()
    private val ticket = Ticket()

    override fun detectIncreaseCount() {
        contract.initializePlusButton(::increaseTicketCount)
    }

    override fun detectDecreaseCount() {
        contract.initializeMinusButton(::decreaseTicketCount)
    }

    override fun deliverMovie() {
        contract.showMovieInformation(movies[movieId])
    }

    override fun deliverReservationHistory() {
        contract.initializeReservationButton(movieId, ticket.count)
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
            is Success -> {
                contract.changeNumberOfTickets(ticket)
                contract.initializeReservationButton(movieId, ticket.count)
            }
            is Failure -> contract.showResultToast()
        }
    }
}

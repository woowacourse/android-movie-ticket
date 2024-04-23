package woowacourse.movie.reservation.detail

import woowacourse.movie.db.Movies
import woowacourse.movie.model.ChangeTicketCountResult
import woowacourse.movie.model.Failure
import woowacourse.movie.model.Success
import woowacourse.movie.model.Ticket

class ReservationDetailPresenter(
    private val contract: ReservationDetailContract.View,
    private val movieId: Int,
) : ReservationDetailContract.Presenter {
    private val movies = Movies.obtainMovies()
    private val ticket = Ticket()

    override fun increaseCount() {
        val result = ticket.increaseCount()
        handleNumberOfTicketsBounds(result, ticket)
    }

    override fun decreaseCount() {
        val result = ticket.decreaseCount()
        handleNumberOfTicketsBounds(result, ticket)
    }

    override fun deliverMovie() {
        contract.showMovieInformation(movies[movieId])
    }

    override fun deliverReservationHistory() {
        contract.initializeReservationButton(movieId, ticket.count)
    }

    private fun handleNumberOfTicketsBounds(
        result: ChangeTicketCountResult,
        ticket: Ticket,
    ) {
        when (result) {
            is Success -> {
                contract.updateCount(ticket.count)
                contract.initializeReservationButton(movieId, ticket.count)
            }
            is Failure -> contract.showErrorToast()
        }
    }
}

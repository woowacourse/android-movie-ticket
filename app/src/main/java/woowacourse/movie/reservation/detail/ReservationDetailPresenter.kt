package woowacourse.movie.reservation.detail

import woowacourse.movie.db.Movies
import woowacourse.movie.model.ChangeTicketCountResult
import woowacourse.movie.model.OutOfRange
import woowacourse.movie.model.InRange
import woowacourse.movie.model.Ticket

class ReservationDetailPresenter(
    private val view: ReservationDetailContract.View,
    private val movieId: Int,
) : ReservationDetailContract.Presenter {
    private val movies = Movies.obtainMovies()
    private val ticket = Ticket()

    override fun increaseCount() {
        val result = ticket.increaseCount()
        handleNumberOfTicketsBounds(result)
    }

    override fun decreaseCount() {
        val result = ticket.decreaseCount()
        handleNumberOfTicketsBounds(result)
    }

    override fun deliverMovie() {
        view.showMovieInformation(movies[movieId])
    }

    override fun deliverReservationInformation() {
        view.moveToReservationFinished(movieId, ticket.count)
    }

    private fun handleNumberOfTicketsBounds(result: ChangeTicketCountResult) {
        when (result) {
            is InRange -> {
                view.updateCount(ticket.count)
                view.moveToReservationFinished(movieId, ticket.count)
            }
            is OutOfRange -> view.showErrorToast()
        }
    }
}

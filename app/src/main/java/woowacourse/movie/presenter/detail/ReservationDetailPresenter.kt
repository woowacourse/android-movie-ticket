package woowacourse.movie.presenter.detail

import woowacourse.movie.db.ScreeningDao
import woowacourse.movie.model.ChangeTicketCountResult
import woowacourse.movie.model.Failure
import woowacourse.movie.model.Success
import woowacourse.movie.model.Ticket

class ReservationDetailPresenter(
    private val view: ReservationDetailContract.View,
    private val dao: ScreeningDao,
) : ReservationDetailContract.Presenter {
    val ticket = Ticket()

    override fun loadMovie(movieId: Int) {
        val movie = dao.find(movieId)
        view.showMovieInformation(movie)
    }

    override fun increaseTicketCount() {
        val result = ticket.increaseCount()
        handleNumberOfTicketsBounds(result)
    }

    override fun decreaseTicketCount() {
        val result = ticket.decreaseCount()
        handleNumberOfTicketsBounds(result)
    }

    override fun initializeReservationButton(movieId: Int) {
        view.navigateToFinished(movieId, ticket)
    }

    override fun handleNumberOfTicketsBounds(result: ChangeTicketCountResult) {
        when (result) {
            is Success -> view.changeNumberOfTickets(ticket)
            is Failure -> view.showResultToast()
        }
    }
}

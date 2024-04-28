package woowacourse.movie.presenter.reservation

import woowacourse.movie.db.screening.ScreeningDao
import woowacourse.movie.model.movie.ScreeningDateTime
import woowacourse.movie.model.result.ChangeTicketCountResult
import woowacourse.movie.model.result.Failure
import woowacourse.movie.model.result.Success
import woowacourse.movie.model.ticket.Ticket

class ReservationDetailPresenter(
    private val view: ReservationDetailContract.View,
    private val dao: ScreeningDao,
) : ReservationDetailContract.Presenter {
    var ticket = Ticket()
        private set

    override fun loadMovie(movieId: Int) {
        val movie = dao.find(movieId)
        view.showMovieInformation(movie)
    }

    override fun loadScreeningPeriod(movieId: Int) {
        val movie = dao.find(movieId)
        view.showScreeningPeriod(movie)
    }

    override fun loadScreeningTimes(
        movieId: Int,
        selectedDate: String,
    ) {
        val movie = dao.find(movieId)
        view.showScreeningTimes(movie, selectedDate)
    }

    override fun increaseTicketCount(count: Int) {
        ticket = Ticket(count)
        val result = ticket.increaseCount()
        handleNumberOfTicketsBounds(result)
    }

    override fun decreaseTicketCount(count: Int) {
        ticket = Ticket(count)
        val result = ticket.decreaseCount()
        handleNumberOfTicketsBounds(result)
    }

    override fun initializeReservationButton(
        movieId: Int,
        dateTime: ScreeningDateTime,
    ) {
        ticket = Ticket(ticket.count, dateTime)
        view.navigateToSeatSelection(movieId, ticket)
    }

    override fun handleNumberOfTicketsBounds(result: ChangeTicketCountResult) {
        when (result) {
            is Success -> view.changeHeadCount(ticket.count)
            is Failure -> view.showResultToast()
        }
    }
}

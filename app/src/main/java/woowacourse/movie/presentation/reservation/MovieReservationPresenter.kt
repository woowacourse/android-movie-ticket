package woowacourse.movie.presentation.reservation

import woowacourse.movie.domain.model.TicketCounter
import woowacourse.movie.domain.repository.MovieRepository

class MovieReservationPresenter(
    private val view: MovieReservationContract.View,
    private val movieId: Int,
    private val movieRepository: MovieRepository,
) : MovieReservationContract.Presenter {
    private val ticketCounter: TicketCounter = TicketCounter()

    override fun loadMovie() {
        view.showMovie(movieRepository.getMovie(movieId))
    }

    override fun decreaseTicketCount() {
        ticketCounter.minusTicketCount()
        view.showCurrentResultTicketCountView()
    }

    override fun increaseTicketCount() {
        ticketCounter.plusTicketCount()
        view.showCurrentResultTicketCountView()
    }

    override fun getTicketCount(): Int {
        return ticketCounter.ticketCount
    }

    companion object {
        const val KEY_NAME_TICKET = "ticket"
    }
}

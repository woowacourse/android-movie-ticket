package woowacourse.movie.presentation.reservation

import woowacourse.movie.domain.model.TicketCounter
import woowacourse.movie.domain.repository.MovieRepository

class MovieReservationPresenter(
    private val view: MovieReservationContract.View,
    private val movieRepository: MovieRepository,
) : MovieReservationContract.Presenter {
    private val model: TicketCounter = TicketCounter()

    val ticketCount
        get() = model.ticketCount

    override fun loadMovie(movieId: Int) {
        view.showMovie(movieRepository.getMovie(movieId))
    }

    override fun clickMinusNumberButton() {
        model.minusTicketCount()
        view.showCurrentResultTicketCountView()
    }

    override fun clickPlusNumberButton() {
        model.plusTicketCount()
        view.showCurrentResultTicketCountView()
    }

    companion object {
        const val KEY_NAME_TICKET = "ticket"
    }
}

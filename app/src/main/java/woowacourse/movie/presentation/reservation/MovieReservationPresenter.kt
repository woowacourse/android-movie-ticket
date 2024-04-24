package woowacourse.movie.presentation.reservation

import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.Ticket
import woowacourse.movie.domain.model.TicketCounter
import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.presentation.reservation.model.TicketModel
import woowacourse.movie.presentation.reservation.model.toTicketModel
import woowacourse.movie.presentation.utils.toLocalDate
import java.time.LocalDate

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

    override fun ticketing(
        title: String,
        screeningDate: LocalDate,
        count: Int,
        price: Int,
    ) {
        val ticket = Ticket(
            title = title,
            screeningDate = screeningDate,
            count = count,
            price = price,
        ).toTicketModel()
        view.moveToTicketDetail(ticket)
    }

    companion object {
        const val KEY_NAME_TICKET = "ticket"
    }
}

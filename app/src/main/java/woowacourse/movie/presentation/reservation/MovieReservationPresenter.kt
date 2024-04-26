package woowacourse.movie.presentation.reservation

import android.os.Bundle
import woowacourse.movie.domain.model.MovieDate
import woowacourse.movie.domain.model.Ticket
import woowacourse.movie.domain.model.TicketCounter
import woowacourse.movie.domain.repository.DateRepository
import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.presentation.model.MovieDateModel
import woowacourse.movie.presentation.model.toMovieDateModel
import woowacourse.movie.presentation.model.toTicketModel
import java.time.LocalDate
import java.time.LocalDateTime

class MovieReservationPresenter(
    private val view: MovieReservationContract.View,
    private val movieId: Int,
    private val movieRepository: MovieRepository,
    private val dateRepository: DateRepository,
) : MovieReservationContract.Presenter {
    private val ticketCounter: TicketCounter = TicketCounter()
    private val movieDate: MovieDate = MovieDate()

    override fun loadMovie() {
        view.showMovie(movieRepository.getMovie(movieId))
    }

    override fun loadDate(
        startDate: LocalDate,
        endDate: LocalDate,
    ) {
        view.showDate(dateRepository.getDatesBetween(startDate, endDate))
    }

    override fun loadTime(currentDate: LocalDate) {
        view.showTime(dateRepository.getDateTimes(currentDate))
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
        count: Int,
    ) {
        val ticket =
            Ticket(
                title = title,
                movieDate = movieDate,
                count = count,
                price = 0,
                seats = listOf(),
            ).toTicketModel()
        view.moveToSeatSelection(ticket)
    }

    override fun selectDate(newDate: LocalDate) {
        movieDate.setCurrentDate(newDate)
    }

    override fun selectTime(newTime: LocalDateTime) {
        movieDate.setCurrentTime(newTime)
    }

    override fun initSavedInstance(
        count: Int,
        movieDateModel: MovieDateModel,
    ) {
        ticketCounter.initTicketCount(count)
        view.showCurrentResultTicketCountView()
        selectDate(movieDateModel.screeningDate)
        selectTime(movieDateModel.screeningTime)
    }

    override fun saveInstance(outState: Bundle) {
        outState.putInt(KEY_TICKET_COUNT, ticketCounter.ticketCount)
        outState.putSerializable(KEY_MOVIE_DATE, movieDate.toMovieDateModel())
    }

    companion object {
        const val KEY_NAME_TICKET = "ticket"
        const val KEY_TICKET_COUNT = "ticketCount"
        const val KEY_MOVIE_DATE = "movieDate"
    }
}

package woowacourse.movie.presentation.reservation

import android.os.Bundle
import woowacourse.movie.domain.DateMaker
import woowacourse.movie.domain.model.MovieDateTime
import woowacourse.movie.domain.model.PendingMovieReservation
import woowacourse.movie.domain.model.TicketCounter
import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.presentation.model.MovieDateModel
import woowacourse.movie.presentation.model.toMovieDateModel
import woowacourse.movie.presentation.model.toPendingMovieReservationModel
import java.time.LocalDate
import java.time.LocalTime

class MovieReservationPresenter(
    private val view: MovieReservationContract.View,
    private val movieId: Int,
    private val movieRepository: MovieRepository,
    private val dateMaker: DateMaker,
) : MovieReservationContract.Presenter {
    private val ticketCounter: TicketCounter = TicketCounter()
    private val movieDateTime: MovieDateTime = MovieDateTime()

    override fun loadMovie() {
        view.showMovie(movieRepository.getMovie(movieId))
    }

    override fun loadDate(
        startDate: LocalDate,
        endDate: LocalDate,
    ) {
        view.showDate(dateMaker.getDatesBetween(startDate, endDate))
    }

    override fun loadTime(currentDate: LocalDate) {
        view.showTime(dateMaker.getDateTimes(currentDate))
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

    override fun reservation(
        title: String,
        count: Int,
    ) {
        val pendingMovieReservation =
            PendingMovieReservation(
                movieId = movieId,
                title = title,
                movieDateTime = movieDateTime,
                count = count,
            ).toPendingMovieReservationModel()
        view.moveToSeatSelection(pendingMovieReservation)
    }

    override fun selectDate(newDate: LocalDate) {
        movieDateTime.setCurrentDate(newDate)
    }

    override fun selectTime(newTime: LocalTime) {
        movieDateTime.setCurrentTime(newTime)
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
        outState.putSerializable(KEY_MOVIE_DATE, movieDateTime.toMovieDateModel())
    }

    companion object {
        const val KEY_NAME_PENDING_RESERVATION = "pendingReservation"
        const val KEY_TICKET_COUNT = "ticketCount"
        const val KEY_MOVIE_DATE = "movieDate"
    }
}

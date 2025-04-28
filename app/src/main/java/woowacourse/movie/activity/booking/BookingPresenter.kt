package woowacourse.movie.activity.booking

import android.os.Bundle
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.MovieSchedule
import woowacourse.movie.domain.TicketManager
import woowacourse.movie.ui.MovieUiModel
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class BookingPresenter(
    private val view: BookingContract.View,
) : BookingContract.Presenter {
    private lateinit var ticketManager: TicketManager
    private lateinit var movieSchedule: MovieSchedule

    override fun initData(movie: Movie) {
        ticketManager = TicketManager(movie)
        movieSchedule = MovieSchedule(movie.startDate, movie.endDate)
        val movieUiModel = MovieUiModel.fromDomain(movie)
        view.setupPage(movieUiModel)

        val dates = movieSchedule.getDates()
        val times = movieSchedule.getTimes(dates.first())

        movieSchedule.setDate(dates.first())
        movieSchedule.setTime(times.first())

        view.updateDateSpinner(dates, 0)
        view.updateTimeSpinner(times, 0)
        view.showTicketCount(ticketManager.getTicketCount())
    }

    override fun getSelectedDate(): String = movieSchedule.getDate()

    override fun getSelectedTime(): String = movieSchedule.getTime()

    override fun confirmBooking() {
        if (ticketManager.getTicketCount() > MINIMUM_TICKET_COUNT) {
            val selectedDateString = movieSchedule.getDate()
            val selectedTimeString = movieSchedule.getTime()
            val date = LocalDate.parse(selectedDateString, DateTimeFormatter.ofPattern("yyyy.M.d"))
            val hour = selectedTimeString.substringBefore(":").toInt()
            val time = LocalTime.of(hour, 0)

            view.moveToSeatSelection(ticketManager.createTicket(date, time))
        }
    }

    override fun decreaseTicketCount() {
        ticketManager.decrementTicketCount()
        view.showTicketCount(ticketManager.getTicketCount())
    }

    override fun increaseTicketCount() {
        ticketManager.incrementTicketCount()
        view.showTicketCount(ticketManager.getTicketCount())
    }

    override fun selectDate(date: String) {
        movieSchedule.setDate(date)
        val times = movieSchedule.getTimes(date)
        movieSchedule.setTime(times.first())

        view.updateTimeSpinner(times, 0)
    }

    override fun selectTime(time: String) {
        movieSchedule.setTime(time)
    }

    override fun saveState(outState: Bundle) {
        outState.putInt(KEY_TICKET_COUNT, ticketManager.getTicketCount())
        outState.putString(KEY_MOVIE_DATE, movieSchedule.getDate())
        outState.putString(KEY_MOVIE_TIME, movieSchedule.getTime())
    }

    override fun restoreState(savedState: Bundle) {
        ticketManager.setTicketCount(savedState.getInt(KEY_TICKET_COUNT))
        val savedDate = savedState.getString(KEY_MOVIE_DATE) ?: ""
        val savedTime = savedState.getString(KEY_MOVIE_TIME) ?: ""

        movieSchedule.setDate(savedDate)
        movieSchedule.setTime(savedTime)

        val dates = movieSchedule.getDates()
        val times = movieSchedule.getTimes(savedDate)

        val selectedDatePosition = dates.indexOf(savedDate).coerceAtLeast(0)
        val selectedTimePosition = times.indexOf(savedTime).coerceAtLeast(0)

        view.updateDateSpinner(dates, selectedDatePosition)
        view.updateTimeSpinner(times, selectedTimePosition)
        view.showTicketCount(ticketManager.getTicketCount())
    }

    companion object {
        private const val MINIMUM_TICKET_COUNT = 0
        private const val KEY_TICKET_COUNT = "TICKET_COUNT"
        private const val KEY_MOVIE_DATE = "MOVIE_DATE"
        private const val KEY_MOVIE_TIME = "MOVIE_TIME"
    }
}

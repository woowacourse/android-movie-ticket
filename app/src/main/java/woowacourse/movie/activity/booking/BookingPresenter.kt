package woowacourse.movie.activity.booking

import android.os.Bundle
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.MovieSchedule
import woowacourse.movie.domain.TicketManager
import woowacourse.movie.ui.MovieUiModel

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
        val times = movieSchedule.getTimes(dates[0])

        view.updateDateSpinner(dates, movieSchedule.getDatePosition())
        view.updateTimeSpinner(times, movieSchedule.getTimePosition())
        view.showTicketCount(ticketManager.getTicketCount())
    }

    override fun getSelectedDate(): Int = movieSchedule.getDatePosition()

    override fun getSelectedTime(): Int = movieSchedule.getTimePosition()

    override fun onConfirmButtonClicked() {
        if (ticketManager.getTicketCount() > MINIMUM_TICKET_COUNT) {
            view.moveToSeatSelection(ticketManager.createTicket())
        }
    }

    override fun onMinusButtonClicked() {
        ticketManager.decrementTicketCount()
        view.showTicketCount(ticketManager.getTicketCount())
    }

    override fun onPlusButtonClicked() {
        ticketManager.incrementTicketCount()
        view.showTicketCount(ticketManager.getTicketCount())
    }

    override fun onDateSelected(position: Int) {
        movieSchedule.setDatePosition(position)
        val selectedDate = movieSchedule.getDates()[position]
        val times = movieSchedule.getTimes(selectedDate)
        view.updateTimeSpinner(times, 0)
    }

    override fun onTimeSelected(position: Int) {
        movieSchedule.setTimePosition(position)
    }

    override fun onSaveState(outState: Bundle) {
        outState.putInt(KEY_TICKET_COUNT, ticketManager.getTicketCount())
        outState.putInt(KEY_MOVIE_DATE_POSITION, movieSchedule.getDatePosition())
        outState.putInt(KEY_MOVIE_TIME_POSITION, movieSchedule.getTimePosition())
    }

    override fun onRestoreState(savedState: Bundle) {
        ticketManager.setTicketCount(savedState.getInt(KEY_TICKET_COUNT))
        val savedDatePosition = savedState.getInt(KEY_MOVIE_DATE_POSITION)
        movieSchedule.setDatePosition(savedDatePosition)
        val savedTimePosition = savedState.getInt(KEY_MOVIE_TIME_POSITION)
        movieSchedule.setTimePosition(savedTimePosition)

        val dates = movieSchedule.getDates()
        val selectedDate = dates[movieSchedule.getDatePosition()]
        val times = movieSchedule.getTimes(selectedDate)

        view.updateDateSpinner(dates, savedDatePosition)
        view.updateTimeSpinner(times, savedTimePosition)
        view.showTicketCount(ticketManager.getTicketCount())
    }

    companion object {
        private const val MINIMUM_TICKET_COUNT = 0
        private const val KEY_TICKET_COUNT = "TICKET_COUNT"
        private const val KEY_MOVIE_DATE_POSITION = "MOVIE_DATE_POSITION"
        private const val KEY_MOVIE_TIME_POSITION = "MOVIE_TIME_POSITION"
    }
}

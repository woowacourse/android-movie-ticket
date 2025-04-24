package woowacourse.movie.activity.booking

import android.os.Bundle
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.TicketManager
import woowacourse.movie.ui.MovieUiModel

class BookingPresenter : BookingContract.Presenter {
    private var view: BookingContract.View? = null
    private lateinit var ticketManager: TicketManager

    override fun attachView(view: BookingContract.View) {
        this.view = view
    }

    override fun initData(movie: Movie) {
        ticketManager = TicketManager(movie)
        val movieUiModel = MovieUiModel.fromDomain(movie)
        view?.setupPage(movieUiModel)

        val dates = ticketManager.getDates()
        val times = ticketManager.getTimes(dates[0])

        view?.updateDateSpinner(dates, ticketManager.getDatePosition())
        view?.updateTimeSpinner(times, ticketManager.getTimePosition())
        view?.showTicketCount(ticketManager.getTicketCount())
    }

    override fun getSelectedDate(): Int = ticketManager.getDatePosition()

    override fun getSelectedTime(): Int = ticketManager.getTimePosition()

    override fun onConfirmButtonClicked() {
        if (ticketManager.getTicketCount() > MINIMUM_TICKET_COUNT) {
            view?.moveToBookingResult(ticketManager.createTicket())
        }
    }

    override fun onMinusButtonClicked() {
        ticketManager.decrementTicketCount()
        view?.showTicketCount(ticketManager.getTicketCount())
    }

    override fun onPlusButtonClicked() {
        ticketManager.incrementTicketCount()
        view?.showTicketCount(ticketManager.getTicketCount())
    }

    override fun onDateSelected(position: Int) {
        ticketManager.setDatePosition(position)
        val selectedDate = ticketManager.getDates()[position]
        val times = ticketManager.getTimes(selectedDate)
        view?.updateTimeSpinner(times, 0)
    }

    override fun onTimeSelected(position: Int) {
        ticketManager.setTimePosition(position)
    }

    override fun onSaveState(outState: Bundle) {
        outState.putInt(KEY_TICKET_COUNT, ticketManager.getTicketCount())
        outState.putInt(KEY_MOVIE_DATE_POSITION, ticketManager.getDatePosition())
        outState.putInt(KEY_MOVIE_TIME_POSITION, ticketManager.getTimePosition())
    }

    override fun onRestoreState(savedState: Bundle) {
        ticketManager.setTicketCount(savedState.getInt(KEY_TICKET_COUNT))
        val savedDatePosition = savedState.getInt(KEY_MOVIE_DATE_POSITION)
        ticketManager.setDatePosition(savedDatePosition)
        val savedTimePosition = savedState.getInt(KEY_MOVIE_TIME_POSITION)
        ticketManager.setTimePosition(savedTimePosition)

        val dates = ticketManager.getDates()
        val selectedDate = dates[ticketManager.getDatePosition()]
        val times = ticketManager.getTimes(selectedDate)

        view?.updateDateSpinner(dates, savedDatePosition)
        view?.updateTimeSpinner(times, savedTimePosition)
        view?.showTicketCount(ticketManager.getTicketCount())
    }

    companion object {
        private const val MINIMUM_TICKET_COUNT = 0
        private const val KEY_TICKET_COUNT = "TICKET_COUNT"
        private const val KEY_MOVIE_DATE_POSITION = "MOVIE_DATE_POSITION"
        private const val KEY_MOVIE_TIME_POSITION = "MOVIE_TIME_POSITION"
    }
}

package woowacourse.movie.presenter.movieReservation

import woowacourse.movie.view.model.TicketUiModel
import java.time.LocalDate
import java.time.LocalTime

interface MovieReservationContract {
    interface View {
        fun showMoviePoster(posterImage: Int)

        fun showMovieTitle(title: String)

        fun showScreeningDates(dateRange: String)

        fun showRunningTime(runningTime: String)

        fun showTicketCount(count: String)

        fun showShowtimeDates(dates: List<LocalDate>)

        fun showShowtimeTimes(
            times: List<LocalTime>,
            savedTime: LocalTime,
        )

        fun setDateSpinner(position: Int)

        fun setTimeSpinner(position: Int)

        fun showTicketCount(count: Int)

        fun goToSeatSelection(ticket: TicketUiModel)
    }

    interface Presenter {
        fun loadReservationInfo()

        fun onInstanceStateRestored(ticket: TicketUiModel)

        fun onDateSelection(date: LocalDate)

        fun onTimeSelection(time: LocalTime)

        fun incrementTicketCount()

        fun decrementTicketCount()

        fun onConfirmSelection()
    }
}

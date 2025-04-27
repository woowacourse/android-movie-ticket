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

        fun showSpinnerDates(dates: List<LocalDate>)

        fun showSpinnerTimes(
            times: List<LocalTime>,
            savedTime: LocalTime,
        )

        fun setTimeSpinner(position: Int)

        fun goToSeatSelection(ticket: TicketUiModel)
    }

    interface Presenter {
        fun loadReservationInfo()

        fun onInstanceStateRestored(ticket: TicketUiModel)

        fun onDateSelection(date: LocalDate)

        fun onTimeSelection(time: LocalTime)

        fun onTicketCountIncrement()

        fun onTicketCountDecrement()

        fun onConfirmSelection()
    }
}

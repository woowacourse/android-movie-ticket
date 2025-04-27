package woowacourse.movie.presenter.movieReservation

import woowacourse.movie.view.model.movie.TicketUiModel
import java.time.LocalDate
import java.time.LocalTime

interface MovieReservationContract {
    interface View {
        fun showSpinnerDates(dates: List<LocalDate>)

        fun showSpinnerTimes(
            times: List<LocalTime>,
            savedTime: LocalTime,
        )

        fun setTimeSpinner(position: Int)

        fun showMoviePoster(posterImage: Int)

        fun showMovieTitle(title: String)

        fun showScreeningDates(dateRange: String)

        fun showRunningTime(runningTime: String)

        fun showTicketCount(count: String)

        fun setIncrementEnabled(canIncrement: Boolean)

        fun setDecrementEnabled(canDecrement: Boolean)

        fun goToSeatSelection(ticket: TicketUiModel)
    }

    interface Presenter {
        fun onViewCreated()

        fun onInstanceStateRestored(ticket: TicketUiModel)

        fun onDateSelection(date: LocalDate)

        fun onTimeSelection(time: LocalTime)

        fun onTicketCountIncrement()

        fun onTicketCountDecrement()

        fun onTicketCountChange()

        fun onConfirmSelection()
    }
}

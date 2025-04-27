package woowacourse.movie.presenter.movieReservation

import woowacourse.movie.view.model.movie.MovieListItem.MovieUiModel
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

        fun showScreeningDates(
            startDate: LocalDate,
            endDate: LocalDate,
        )

        fun showRunningTime(runningTime: Int)

        fun showTicketCount(count: String)

        fun setIncrementEnabled(canIncrement: Boolean)

        fun setDecrementEnabled(canDecrement: Boolean)

        fun goToSeatSelection(ticket: TicketUiModel)
    }

    interface Presenter {
        fun onViewCreated(movie: MovieUiModel)

        fun onInstanceStateRestored(ticket: TicketUiModel)

        fun onDateSelection(date: LocalDate)

        fun onTimeSelection(time: LocalTime)

        fun onTicketCountIncrement()

        fun onTicketCountDecrement()

        fun onTicketCountChange()

        fun onConfirmSelection()
    }
}

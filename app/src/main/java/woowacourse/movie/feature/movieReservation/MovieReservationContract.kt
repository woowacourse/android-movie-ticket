package woowacourse.movie.feature.movieReservation

import woowacourse.movie.feature.model.movie.MovieListItem.MovieUiModel
import woowacourse.movie.feature.model.movie.TicketUiModel
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

        fun showTicketCount(count: Int)

        fun setIncrementEnabled(canIncrement: Boolean)

        fun setDecrementEnabled(canDecrement: Boolean)

        fun goToSeatSelection(ticket: TicketUiModel)
    }

    interface Presenter {
        fun loadReservationInfo(movie: MovieUiModel)

        fun restoreReservationInfo(ticket: TicketUiModel)

        fun selectDate(date: LocalDate)

        fun selectTime(time: LocalTime)

        fun incrementTicketCount()

        fun decrementTicketCount()

        fun updateTicketCountControls()

        fun confirmSelection()
    }
}

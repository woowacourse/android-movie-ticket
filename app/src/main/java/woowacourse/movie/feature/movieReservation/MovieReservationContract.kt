package woowacourse.movie.feature.movieReservation

import woowacourse.movie.feature.model.movie.MovieListItem.MovieUiModel
import woowacourse.movie.feature.model.movie.TicketUiModel
import java.time.LocalDate
import java.time.LocalTime

interface MovieReservationContract {
    interface View {
        fun loadSpinnerDates(dates: List<LocalDate>)

        fun loadSpinnerTimes(
            times: List<LocalTime>,
            savedTime: LocalTime,
        )

        fun setTimeSpinner(position: Int)

        fun showReservationInfo(ticket: TicketUiModel)

        fun updateTicketCount(count: Int)

        fun setIncrementEnabled(canIncrement: Boolean)

        fun setDecrementEnabled(canDecrement: Boolean)

        fun goToSeatSelection(ticket: TicketUiModel)
    }

    interface Presenter {
        fun initializeReservationInfo(movie: MovieUiModel)

        fun loadReservationInfo(ticket: TicketUiModel)

        fun selectDate(date: LocalDate)

        fun selectTime(time: LocalTime)

        fun incrementTicketCount()

        fun decrementTicketCount()

        fun confirmSelection()
    }
}

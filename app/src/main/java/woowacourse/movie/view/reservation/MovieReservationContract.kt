package woowacourse.movie.view.reservation

import woowacourse.movie.view.movie.model.MovieUiModel
import woowacourse.movie.view.reservation.ticket.TicketUiModel
import java.time.LocalDate
import java.time.LocalTime

interface MovieReservationContract {
    interface View {
        fun showHeadCount(count: Int)

        fun showMovieInfo(movie: MovieUiModel)

        fun updateDateSpinner(
            screeningDates: List<LocalDate>,
            selectedDate: LocalDate,
        )

        fun updateTimeSpinner(
            showtimes: List<LocalTime>,
            selectedTime: LocalTime,
        )

        fun navigateToCompleteScreen(ticket: TicketUiModel)
    }

    interface Presenter {
        fun loadMovieReservationScreen()

        fun onClickIncrementButton()

        fun onClickDecrementButton()

        fun onSelectDate(date: LocalDate)

        fun onSelectTime(time: LocalTime)

        fun completeReservation()
    }
}

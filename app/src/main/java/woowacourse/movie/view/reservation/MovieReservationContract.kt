package woowacourse.movie.view.reservation

import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Ticket
import java.time.LocalDate
import java.time.LocalTime

interface MovieReservationContract {
    interface View {
        fun showHeadCount(count: Int)

        fun showMovieInfo(movie: Movie)

        fun updateDateSpinner(
            screeningDates: List<LocalDate>,
            selectedDate: LocalDate,
        )

        fun updateTimeSpinner(
            showtimes: List<LocalTime>,
            selectedTime: LocalTime,
        )

        fun navigateToCompleteScreen(ticket: Ticket)
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

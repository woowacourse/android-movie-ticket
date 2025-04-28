package woowacourse.movie.booking.detail

import woowacourse.movie.domain.Ticket
import woowacourse.movie.movies.MovieUiModel
import java.time.LocalDate
import java.time.LocalTime

interface BookingDetailContract {
    interface Presenter {
        fun loadMovie(movieUiModel: MovieUiModel)

        fun loadTicketCount()

        fun loadDateList()

        fun setTicketCount(count: Int)

        fun onDateSelected(date: LocalDate)

        fun loadTimeList()

        fun onTimeSelected(time: LocalTime)

        fun onIncreaseTicketCount()

        fun onDecreaseTicketCount()

        fun onSelectComplete()

        fun getTicketCount(): Int
    }

    interface View {
        fun showMovieInfo(movieUiModel: MovieUiModel)

        fun showDateSpinnerItems(dates: List<LocalDate>)

        fun showTimeSpinnerItems(times: List<LocalTime>)

        fun showTicketCount(count: Int)

        fun navigateToBookingSeat(ticket: Ticket)
    }
}

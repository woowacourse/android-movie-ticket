package woowacourse.movie.presentation.booking

import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.MovieTicket
import java.time.LocalDate
import java.time.LocalTime

interface BookingContract {
    interface View {
        fun initBooking()
        fun showMovie(movie: Movie)
        fun showBookableDates(dates: List<LocalDate>)
        fun showBookableTimes(times: List<LocalTime>)
        fun updateHeadCount(count: Int)
        fun showConfirmDialog()
        fun navigateToBookingSummary(ticket: MovieTicket)
    }

    interface Presenter {
        fun onViewCreated()
        fun onDateSelected(selectedDate: LocalDate)
        fun onTimeSelected(selectedTime: LocalTime)
        fun onIncreaseHeadCount()
        fun onDecreaseHeadCount()
        fun onConfirmClicked()
        fun onConfigurationChanged(count: Int?, date: LocalDate?, time: LocalTime?)
    }
}
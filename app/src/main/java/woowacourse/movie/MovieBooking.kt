package woowacourse.movie

import woowacourse.movie.domain.BookingStatus
import woowacourse.movie.domain.Movie
import java.time.LocalDate
import java.time.LocalTime

interface MovieBooking {
    interface View {
        fun showMovieInfo()
        fun updateMemberCount(count: Int)
        fun showBookingDate(dates: List<LocalDate>)
        fun showBookingTimes(times: List<LocalTime>)
        fun showConfirmDialog(bookingStatus: BookingStatus)
        fun navigateToMovieBooked(bookingStatus: BookingStatus)
        fun showError(messageRes: Int)
    }

    interface Presenter {
        fun loadMovie(movie: Movie)
        fun increaseCount()
        fun decreaseCount()
        fun selectDate(date: LocalDate)
        fun selectTime(time: LocalTime)
        fun confirmBooking()
    }
}

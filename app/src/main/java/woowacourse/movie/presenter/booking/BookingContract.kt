package woowacourse.movie.presenter.booking

import woowacourse.movie.domain.model.booking.Booking
import woowacourse.movie.domain.model.movies.Movie
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

interface BookingContract {
    interface View {
        fun showMovieDetail(movie: Movie)

        fun showPeopleCount(count: Int)

        fun showScreeningDate(screeningBookingDates: List<LocalDate>)

        fun showScreeningTime(screeningBookingTimes: List<LocalTime>)

        fun showToast()

        fun moveToBookingComplete(booking: Booking)
    }

    interface Presenter {
        fun loadMovieDetail(index: Int)

        fun loadPeopleCount()

        fun loadScreeningDate(
            startDate: LocalDate,
            endDate: LocalDate,
            now: LocalDateTime,
        )

        fun loadScreeningTime(
            selectedDate: LocalDate,
            now: LocalDateTime,
        )

        fun loadBooking(
            title: String,
            bookingDate: String,
            bookingTime: String,
            peopleCount: String,
        )

        fun decreasePeopleCount()

        fun increasePeopleCount(limit: Int)

        fun restorePeopleCount(savedCount: Int)
    }
}

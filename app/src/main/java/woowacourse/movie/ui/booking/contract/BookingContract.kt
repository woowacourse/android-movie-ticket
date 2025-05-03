package woowacourse.movie.ui.booking.contract

import woowacourse.movie.domain.model.Headcount
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.ui.booking.model.BookingState
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

interface BookingContract {
    interface Presenter {
        fun decreaseHeadcount()

        fun increaseHeadcount()

        fun restoreState(bookingState: BookingState)

        fun savedBookingState(): BookingState

        fun loadSelectedDatePosition(selectedDatePosition: Int)

        fun loadSelectedTimePosition(selectedTimePosition: Int)

        fun loadSelectedLocalDateTime(selectedDateTime: LocalDateTime)

        fun completeBooking()

        fun refreshMovieInfo()

        fun restoreHeadcount(headcount: Headcount)

        fun refreshHeadcountDisplay()

        fun setupDateSpinner()

        fun setupTimeSpinner()
    }

    interface View {
        fun setMovieInfoViews(movie: Movie)

        fun updateHeadcountDisplay(headcount: Headcount)

        fun setDateSpinner(
            spinnerItems: List<LocalDate>,
            position: Int,
        )

        fun setTimeSpinner(
            spinnerItems: List<LocalTime>,
            position: Int,
        )

        fun startBookingSeatActivity(
            movieTitle: String,
            dateTime: LocalDateTime,
            headcount: Headcount,
        )
    }
}

package woowacourse.movie.view.booking

import woowacourse.movie.domain.model.BookedTicket
import woowacourse.movie.domain.model.Headcount
import woowacourse.movie.domain.model.Movie
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

interface BookingContract {
    interface Presenter {
        fun decreaseHeadcount()

        fun increaseHeadcount()

        fun fetchMovie(): Movie

        fun updateMovieInfoViews()

        fun updateHeadcountTextView()

        fun updateDateSpinner()

        fun updateTimeSpinner()

        fun completeBooking()
    }

    interface View {
        fun getMovie(): Movie?

        fun getSelectedDate(): LocalDate

        fun getSelectedTimePosition(): Int

        fun getSelectedDateTime(): LocalDateTime

        fun setMovieInfoViews(movie: Movie)

        fun setHeadcountTextView(headcount: Headcount)

        fun setDateSpinner(
            spinnerItems: List<LocalDate>,
            position: Int,
        )

        fun setTimeSpinner(
            spinnerItems: List<LocalTime>,
            position: Int,
        )

        fun moveToBookingCompleteActivity(bookedTicket: BookedTicket)
    }
}

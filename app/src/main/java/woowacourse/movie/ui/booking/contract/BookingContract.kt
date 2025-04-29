package woowacourse.movie.ui.booking.contract

import woowacourse.movie.domain.model.Headcount
import woowacourse.movie.domain.model.Movie
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

interface BookingContract {
    interface Presenter {
        fun decreaseHeadcount()

        fun increaseHeadcount()

        fun loadMovie(): Movie

        fun completeBooking()

        fun refreshMovieInfo()

        fun restoreHeadcount(headcount: Headcount)

        fun refreshHeadcountDisplay()

        fun setupDateSpinner()

        fun setupTimeSpinner()

        fun setSelectedDatePosition(position: Int)

        fun setSelectedTimePosition(position: Int)
    }

    interface View {
        fun getMovie(): Movie?

        fun getSelectedDate(): LocalDate

        fun getSelectedTimePosition(): Int

        fun getSelectedDateTime(): LocalDateTime

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

package woowacourse.movie.ui.booking

import woowacourse.movie.domain.movies.Movie
import woowacourse.movie.domain.movies.MovieToReserve
import java.time.LocalDate
import java.time.LocalTime

interface BookingContract {
    interface View {
        fun showSelectedMovie(movie: Movie)

        fun showErrorDialog()

        fun updateHeadCount(headCount: Int)

        fun navigateToSeatsSelection(movieToReserve: MovieToReserve)

        fun updateDateSpinner(
            dates: List<LocalDate>,
            index: Int,
        )

        fun updateTimeSpinner(
            times: List<LocalTime>,
            index: Int,
        )
    }

    interface Presenter {
        fun loadInitialHeadCount()

        fun increaseHeadCount()

        fun decreaseHeadCount()

        fun restoreHeadCount(restoredCount: Int)

        fun restoreDate(restoredDate: LocalDate?)

        fun restoreTime(restoredTime: LocalTime?)

        fun confirmMovieReservation()

        fun loadAvailableDates()

        fun loadAvailableTimes(selectedDate: LocalDate)

        fun loadSelectedMovie()
    }
}

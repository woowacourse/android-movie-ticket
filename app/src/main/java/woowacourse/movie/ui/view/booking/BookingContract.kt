package woowacourse.movie.ui.view.booking

import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.MovieTicket
import java.time.LocalDate
import java.time.LocalTime

interface BookingContract {
    interface View {
        fun showSelectedMovie()

        fun showErrorDialog()

//        fun showBookingConfirmDialog()

        fun updateHeadCount(headCount: Int)

        fun navigateToSeatsSelection(movieTicket: MovieTicket)

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
        fun getHeadCount(): Int

        fun loadInitialHeadCount()

        fun increaseHeadCount()

        fun decreaseHeadCount()

        fun saveHeadCount(restoredCount: Int)

        fun saveDate(restoredDate: LocalDate)

        fun saveTime(restoredTime: LocalTime)

        fun getMovie(): Movie

        fun tryLoadMovie(movieId: Int): Boolean

        fun onConfirm()

        fun loadAvailableDates()

        fun loadAvailableTimes(selectedDate: LocalDate)
    }
}

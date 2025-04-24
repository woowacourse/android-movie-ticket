package woowacourse.movie.ui.view.booking

import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.MovieTicket
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

interface BookingContract {
    interface View {
        fun showSelectedMovie()

        fun showErrorDialog()

        fun showBookingConfirmDialog()

        fun updateHeadCount(headCount: Int)

        fun navigateToSummary(movieTicket: MovieTicket)

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

        fun restoreHeadCount(restoredCount: Int)

        fun getMovie(): Movie

        fun tryLoadMovie(movieId: Int): Boolean

        fun onConfirm(screeningDateTime: LocalDateTime)

        fun loadAvailableDates(selectedDate: LocalDate)

        fun loadAvailableTimes(
            selectedDate: LocalDate,
            selectedTime: LocalTime,
        )
    }
}

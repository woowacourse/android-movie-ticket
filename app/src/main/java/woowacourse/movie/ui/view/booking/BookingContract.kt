package woowacourse.movie.ui.view.booking

import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.ReservedMovie
import java.time.LocalDate
import java.time.LocalTime

interface BookingContract {
    interface View {
        fun showSelectedMovie(movie: Movie)

        fun showErrorDialog()

        fun updateHeadCount(headCount: Int)

        fun navigateToSeatsSelection(reservedMovie: ReservedMovie)

        fun updateDateSpinner(
            dates: List<LocalDate>,
            index: Int,
        )

        fun updateTimeSpinner(
            times: List<LocalTime>,
            index: Int,
        )

//        fun showSavedBookingInfo(
//            headCount: Int,
//            selectedDate: LocalDate,
//            selectedTime: LocalTime,
//        )
    }

    interface Presenter {
        fun loadInitialHeadCount()

        fun increaseHeadCount()

        fun decreaseHeadCount()

        fun saveHeadCount(restoredCount: Int)

        fun saveDate(restoredDate: LocalDate?)

        fun saveTime(restoredTime: LocalTime?)

        fun onConfirm()

        fun loadAvailableDates()

        fun loadAvailableTimes(selectedDate: LocalDate)

        fun loadSelectedMovie()
    }
}

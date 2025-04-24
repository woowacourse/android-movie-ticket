package woowacourse.movie.ui.view.booking

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
        fun increaseHeadCount()

        fun decreaseHeadCount()

        fun onConfirm(
            id: Int,
            screeningDateTime: LocalDateTime,
            headCount: Int,
        )

        fun loadAvailableDates(
            startDate: LocalDate,
            endDate: LocalDate,
            selectedDate: LocalDate,
        )

        fun loadAvailableTimes(
            selectedDate: LocalDate,
            selectedTime: LocalTime,
        )
    }
}

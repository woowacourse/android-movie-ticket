package woowacourse.movie.presentation.view.reservation.detail

import woowacourse.movie.presentation.model.MovieUiModel
import woowacourse.movie.presentation.model.ReservationInfoUiModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

interface ReservationDetailContract {
    interface Presenter {
        fun fetchData(
            initCount: Int? = null,
            dateTime: LocalDateTime? = null,
            getMovie: () -> MovieUiModel?,
        )

        fun updateReservationCount(updateCount: Int)

        fun onSelectDate(
            date: LocalDate,
            selectedTime: LocalTime? = null,
        )

        fun onReserve(reservationDateTime: LocalDateTime)
    }

    interface View {
        fun setScreen(movie: MovieUiModel)

        fun updateDateSpinner(
            dates: List<LocalDate>,
            times: List<LocalTime>,
            selectedDateTime: LocalDateTime? = null,
        )

        fun updateTimeSpinner(
            times: List<LocalTime>,
            selectedTime: LocalTime? = null,
        )

        fun updateReservationCount(
            count: Int,
            isClickable: Boolean,
        )

        fun showNoAvailableTimesDialog()

        fun showReservationConfirmationDialog()

        fun navigateToResult(reservationInfo: ReservationInfoUiModel)
    }
}

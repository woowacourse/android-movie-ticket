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

        fun updateDates(
            dates: List<LocalDate>,
            times: List<LocalTime>,
            selectedDateTime: LocalDateTime? = null,
        )

        fun updateTimes(
            times: List<LocalTime>,
            selectedTime: LocalTime? = null,
        )

        fun updateReservationCount(
            count: Int,
            isEnabled: Boolean,
        )

        fun notifyNoAvailableDates()

        fun notifyReservationConfirm(reservationInfo: ReservationInfoUiModel)
    }
}

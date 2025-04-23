package woowacourse.movie.view.reservation

import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.ReservationInfo
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

interface ReservationContract {
    interface Presenter {
        fun fetchData(
            initCount: Int? = null,
            dateTime: LocalDateTime? = null,
            getMovie: () -> Movie?,
        )

        fun updateReservationCount(updateCount: Int)

        fun onSelectDate(
            date: LocalDate,
            selectedTime: LocalTime? = null,
        )

        fun onReserve(reservationDateTime: LocalDateTime)
    }

    interface View {
        fun setScreen(movie: Movie)

        fun updateDateSpinner(
            dates: List<LocalDate>,
            times: List<LocalTime>,
            selectedDateTime: LocalDateTime? = null,
        )

        fun updateTimeSpinner(
            times: List<LocalTime>,
            selectedTime: LocalTime? = null,
        )

        fun updateReservationCount(count: Int)

        fun showNoAvailableTimesDialog()

        fun showReservationConfirmationDialog()

        fun navigateToResult(reservationInfo: ReservationInfo)
    }
}

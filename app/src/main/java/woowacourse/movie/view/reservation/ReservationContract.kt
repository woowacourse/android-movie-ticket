package woowacourse.movie.view.reservation

import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.ReservationInfo
import java.time.LocalDate
import java.time.LocalTime

interface ReservationContract {
    interface View {
        fun showMovieDetail(movie: Movie)

        fun updateReservationCount(count: Int)

        fun updateDateSet(
            dates: List<LocalDate>,
            selectedDate: LocalDate? = null,
        )

        fun updateTimeSet(
            times: List<LocalTime>,
            selectedTime: LocalTime? = null,
        )

        fun notifyUnavailableDate()

        fun navigateToSeatSelectionScreen(reservationInfo: ReservationInfo)

        fun notifyInvalidReservationInfo()
    }

    interface Presenter {
        fun loadData(
            movie: Movie?,
            count: Int? = null,
            dateTime: String? = null,
        )

        fun increaseCount(count: Int)

        fun decreaseCount(count: Int)

        fun selectDate(date: LocalDate)

        fun onReserve(
            reservationDate: LocalDate?,
            reservationTime: LocalTime?,
        )
    }
}

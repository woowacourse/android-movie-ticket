package woowacourse.movie.reserve

import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Reservation
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

interface ReservationContract {
    interface View {
        fun showMovieInfo(movie: Movie)

        fun showTicketCount(count: Int)

        fun initDateSpinner(
            dates: List<LocalDate>,
            reservedDate: LocalDate,
        )

        fun updateTimeSpinner(
            times: List<LocalTime>,
            reservedTime: LocalTime,
        )
    }

    interface Presenter {
        fun initReservation(
            reservation: Reservation?,
            movie: Movie,
        )

        fun updateReservationInfo()

        fun updateReservableDates()

        fun updateReservableTimes(selectedDate: LocalDate)

        fun increaseTicketCount()

        fun decreaseTicketCount()

        fun updateReservedTime(dateTime: LocalDateTime)
    }
}

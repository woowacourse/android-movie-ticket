package woowacourse.movie.reserve

import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Reservation
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

interface ReserveContract {
    interface View {
        fun initMovieInfo(movie: Movie)

        fun initTimeSpinner()

        fun updateTimeSpinner(
            times: List<LocalTime>,
            reservedTime: LocalTime,
        )

        fun initDateSpinner(
            dates: List<LocalDate>,
            reservedDate: LocalDate,
        )

        fun updateTicketCount(count: String)

        fun initButtonClickListeners()
    }

    interface Presenter {
        fun initReservation(
            reservation: Reservation?,
            movie: Movie,
        )

        fun initView()

        fun updateDateSpinner()

        fun updateTimeSpinner(selectedDate: LocalDate)

        fun onPlusButtonClick()

        fun onMinusButtonClick()

        fun updateReservedTime(dateTime: LocalDateTime)
    }
}

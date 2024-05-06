package woowacourse.movie.reservation

import java.time.LocalDate
import java.time.LocalTime

interface ReservationFinishedContract {
    interface View {
        fun showMovieInformation(movieTitle: String)

        fun showReservationInformation(
            people: Int,
            seats: String,
            totalPrice: Int,
        )

        fun showReservationSchedule(
            screeningDate: LocalDate,
            screeningTime: LocalTime,
        )
    }

    interface Presenter {
        fun loadMovieInformation()

        fun loadReservationInformation()

        fun loadReservationSchedule()
    }
}

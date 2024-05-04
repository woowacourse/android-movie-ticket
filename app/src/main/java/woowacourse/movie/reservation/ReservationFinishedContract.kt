package woowacourse.movie.reservation

import java.time.LocalDate
import java.time.LocalTime

interface ReservationFinishedContract {
    interface View {
        fun showReservationInformation(
            movieTitle: String,
            screeningDate: LocalDate,
            screeningTime: LocalTime,
            people: Int,
            seats: String,
            totalPrice: Int,
        )
    }

    interface Presenter {
        fun loadReservationInformation()
    }
}

package woowacourse.movie.ui.reserve

import woowacourse.movie.domain.model.movie.Movie
import woowacourse.movie.domain.model.reservation.Reservation
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

interface ReserveContract {
    interface Presenter {
        fun initMovie(movie: Movie)

        fun initReservationData(selectedDateTime: () -> LocalDateTime)

        fun initDateSpinner(currentDate: LocalDate)

        fun initTimeSpinner(
            startDate: LocalDate,
            currentDateTime: LocalDateTime,
        )

        fun updateTicketCount()

        fun increasePurchaseCount()

        fun decreasePurchaseCount()

        fun reserve()

        fun dateOnClick(
            date: LocalDate,
            currentDateTime: LocalDateTime,
            selectedDateTime: () -> LocalDateTime,
        )

        fun timeOnClick(
            position: Int,
            selectedDateTime: () -> LocalDateTime,
        )
    }

    interface View {
        fun initScreen(movie: Movie)

        fun fetchDates(dates: List<LocalDate>)

        fun fetchTimes(times: List<LocalTime>)

        fun fetchPurchaseCount(purchaseCount: Int)

        fun reserve(
            reservation: Reservation,
            purchaseCount: Int,
        )

        fun showToast()

        fun dateOnClick(
            date: LocalDate,
            screeningTimesSize: Int,
        )

        fun timeOnClick(position: Int)
    }
}

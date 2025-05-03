package woowacourse.movie.ui.reserve

import woowacourse.movie.domain.model.reservation.Reservation
import woowacourse.movie.ui.model.MovieUiModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

interface ReserveContract {
    interface Presenter {
        fun initMovie(movieUiModel: MovieUiModel)

        fun updateReservation(selectedDateTime: LocalDateTime)

        fun initDates(currentDate: LocalDate)

        fun initTimes(
            startDate: LocalDate,
            currentDateTime: LocalDateTime,
        )

        fun updateTicketCount()

        fun increasePurchaseCount()

        fun decreasePurchaseCount()

        fun reserve()

        fun updateSelectedDate(
            date: LocalDate,
            currentDateTime: LocalDateTime,
        )

        fun updateSelectedTime(position: Int)
    }

    interface View {
        fun initScreen(movie: MovieUiModel)

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

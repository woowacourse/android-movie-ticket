package woowacourse.movie.booking.detail

import woowacourse.movie.movie.MovieUiModel
import java.time.LocalDate
import java.time.LocalTime

interface BookingDetailContract {
    interface View {
        fun showMovieInfo(movie: MovieUiModel)

        fun showTicket(result: TicketUiModel)

        fun showScreeningDates(
            dates: List<LocalDate>,
            selected: LocalDate,
        )

        fun showScreeningTimes(
            times: List<LocalTime>,
            selected: LocalTime,
        )

        fun startSeatSelectionActivity(result: TicketUiModel)

        fun showToastErrorAndFinish(message: String)
    }

    interface Presenter {
        fun initializeData()

        fun createDefaultTicket()

        fun restoreTicketData(
            headCount: Int,
            screeningDate: String?,
            screeningTime: String?,
        )

        fun selectDate(date: LocalDate)

        fun selectTime(time: LocalTime)

        fun increaseHeadCount()

        fun decreaseHeadCount()

        fun confirmReservation()

        fun getCurrentTicketUiModel(): TicketUiModel
    }
}

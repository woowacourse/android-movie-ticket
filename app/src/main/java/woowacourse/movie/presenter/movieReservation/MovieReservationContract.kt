package woowacourse.movie.presenter.movieReservation

import woowacourse.movie.view.model.TicketUiModel
import java.time.LocalDate
import java.time.LocalTime

interface MovieReservationContract {
    interface View {
        fun showReservationInfo(ticket: TicketUiModel)

        fun showScreeningDates(dates: List<LocalDate>)

        fun showScreeningTimes(
            times: List<LocalTime>,
            savedTime: LocalTime,
        )

        fun setDateSpinner(position: Int)

        fun setTimeSpinner(position: Int)

        fun showTicketCount(count: Int)

        fun goToSeatSelection(ticket: TicketUiModel)
    }

    interface Presenter {
        fun loadReservationInfo()

        fun onInstanceStateRestored(ticket: TicketUiModel)

        fun onDateSelection(date: LocalDate)

        fun onTimeSelection(time: LocalTime)

        fun incrementTicketCount()

        fun decrementTicketCount()

        fun onConfirmSelection()
    }
}

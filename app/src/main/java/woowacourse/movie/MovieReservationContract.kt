package woowacourse.movie

import woowacourse.movie.view.model.TicketUiModel
import java.time.LocalDate
import java.time.LocalTime

interface MovieReservationContract {
    interface View {
        fun showReservationInfo(ticket: TicketUiModel)

        fun showScreeningDates(dates: List<LocalDate>)

        fun showScreeningTimes(times: List<LocalTime>)

        fun showTicketCount(count: Int)

        fun showAlertDialog()

        fun reserveMovie()

        fun confirmReservation(ticket: TicketUiModel)
    }

    interface Presenter {
        fun loadReservationInfo()

        fun onDateSelection(date: LocalDate)

        fun onTimeSelection(time: LocalTime)

        fun incrementTicketCount()

        fun decrementTicketCount()

        fun onReservation()

        fun onReservationConfirmation()
    }
}

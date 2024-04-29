package woowacourse.movie.presenter.contract

import woowacourse.movie.model.screening.Screening
import woowacourse.movie.view.state.TicketingForm
import java.time.LocalDate
import java.time.LocalTime

typealias SelectedDate = String
typealias SelectedTime = String

interface TicketingContract {
    interface View {
        fun assignInitialView(
            screening: Screening,
            count: Int,
            date: LocalDate?,
            time: LocalTime?,
        )

        fun updateCount(count: Int)

        fun navigateToSeatSelection(ticketingForm: TicketingForm)

        fun showToastMessage(message: String)

        fun updateAvailableTimes(times: List<LocalTime>)
    }

    interface Presenter {
        fun initializeTicketingData(
            screeningId: Long,
            initialCount: Int,
            selectedDate: SelectedDate? = null,
            selectedTime: SelectedTime? = null,
        )

        fun decreaseCount()

        fun increaseCount()

        fun reserveTickets()

        fun updateDate(date: String)

        fun updateTime(time: String)
    }
}

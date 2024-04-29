package woowacourse.movie.presenter.contract

import woowacourse.movie.model.screening.Screening
import woowacourse.movie.view.state.TicketingForm
import woowacourse.movie.view.utils.ErrorMessage
import java.time.LocalTime

interface TicketingContract {
    interface View {
        fun assignInitialView(
            screening: Screening,
            count: Int,
        )

        fun updateCount(count: Int)

        fun navigateToSeatSelection(ticketingForm: TicketingForm)

        fun showToastMessage(error: ErrorMessage)

        fun updateAvailableTimes(times: List<LocalTime>)
    }

    interface Presenter {
        fun initializeTicketingData(
            screeningId: Long,
            initialCount: Int,
            selectedDate: String? = null,
            selectedTime: String? = null,
        )

        fun decreaseCount()

        fun increaseCount()

        fun reserveTickets()

        fun updateDate(date: String)

        fun updateTime(time: String)
    }
}

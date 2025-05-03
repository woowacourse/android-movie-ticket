package woowacourse.movie.booking

import android.os.Bundle
import woowacourse.movie.model.TicketCount
import woowacourse.movie.uiModel.TicketUIModel
import java.time.LocalDate

interface BookingContract {
    interface View {
        fun navigateToResult(ticket: TicketUIModel)

        fun repairInstanceState(state: Bundle)

        fun changeTicketCount(ticketCountValue: TicketCount)

        fun showAvailableDate(
            startDate: LocalDate,
            endDate: LocalDate,
        )

        fun showAvailableTime(selectedDate: LocalDate)

        fun setupDateChangeListener()

        fun confirmButtonHandler()

        fun countButtonHandler()
    }

    interface Presenter {
        fun onCreateView(savedInstanceState: Bundle?)

        fun onBookButtonClick(
            title: String,
            date: String,
            time: String,
            count: TicketCount,
        )

        fun upTicketCount(ticketCount: TicketCount)

        fun downTicketCount(ticketCount: TicketCount)

        fun changeTimesByDate(selectedDate: LocalDate)
    }
}

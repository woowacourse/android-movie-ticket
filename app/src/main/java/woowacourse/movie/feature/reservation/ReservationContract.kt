package woowacourse.movie.feature.reservation

import woowacourse.movie.feature.movieSelect.adapter.ScreeningData
import woowacourse.movie.model.movieSelect.screening.Screening
import woowacourse.movie.model.ticket.TicketCount
import woowacourse.movie.view.model.TicketData
import java.time.LocalDate

interface ReservationContract {
    interface View {
        fun getScreeningData(): ScreeningData

        fun initScreeningInfoUI(screeningData: ScreeningData)

        fun setDateSelectUi(screening: Screening)

        fun setTimeSelectUi(
            selectedDate: LocalDate,
            screening: Screening,
            position: Int,
        )

        fun setTicketCounterUi(ticketCount: TicketCount)

        fun initTicketPlusBtnUi()

        fun initTicketMinusBtnUi()

        fun printError(message: String)

        fun navigateToSelectSeatUI(ticketData: TicketData)
    }
}

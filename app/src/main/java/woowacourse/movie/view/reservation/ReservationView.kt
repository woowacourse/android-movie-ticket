package woowacourse.movie.view.reservation

import woowacourse.movie.model.movie.screening.Screening
import woowacourse.movie.model.ticket.TicketCount
import woowacourse.movie.view.model.ScreeningData
import woowacourse.movie.view.model.TicketData
import java.time.LocalDate

interface ReservationView {
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

    fun navigateToTicketUI(ticketData: TicketData)
}

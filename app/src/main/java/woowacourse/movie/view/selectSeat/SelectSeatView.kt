package woowacourse.movie.view.selectSeat

import woowacourse.movie.view.model.TicketData

interface SelectSeatView {
    fun getTicketData(): TicketData

    fun initMovieTitleUI(ticketData: TicketData)

    fun printError(message: String)

    fun navigateToTicketUI(ticketData: TicketData)
}

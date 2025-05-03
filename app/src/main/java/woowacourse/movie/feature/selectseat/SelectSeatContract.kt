package woowacourse.movie.feature.selectseat

import woowacourse.movie.model.ticket.TicketPrice
import woowacourse.movie.model.ticket.seat.Seat
import woowacourse.movie.view.model.TicketData

interface SelectSeatContract {
    interface View {
        fun getTicketData(): TicketData

        fun initMovieTitleUI(ticketData: TicketData)

        fun initSeatClickListener()

        fun seatSelect(seat: Seat)

        fun seatUnSelect(seat: Seat)

        fun setTicketPrice(ticketPrice: TicketPrice)

        fun printError(message: String)

        fun navigateToTicketUI(ticketData: TicketData)

        fun updateSubmitButton()
    }
}

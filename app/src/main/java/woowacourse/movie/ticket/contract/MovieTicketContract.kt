package woowacourse.movie.ticket.contract

import woowacourse.movie.reservation.model.Count
import woowacourse.movie.ticket.model.Ticket

interface MovieTicketContract {
    interface View {
        val presenter: Presenter

        fun showTicketView(
            info: Ticket,
            ticketCount: Count,
        )

        fun showScreeningDate(info: String)

        fun showScreeningTime(info: String)
    }

    interface Presenter {
        fun storeTicketCount(count: Count)

        fun setTicketInfo()

        fun storeScreeningDate(date: String)

        fun storeScreeningTime(time: String)

        fun setScreeningDateInfo()

        fun setScreeningTimeInfo()
    }
}

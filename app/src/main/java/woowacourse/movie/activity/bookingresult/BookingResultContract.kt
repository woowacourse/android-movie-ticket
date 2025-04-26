package woowacourse.movie.activity.bookingresult

import woowacourse.movie.domain.Ticket

interface BookingResultContract {
    interface View {
        fun showTicketInfo(ticket: Ticket)
    }

    interface Presenter {
        fun attachView(view: View)

        fun loadTicket(ticket: Ticket)
    }
}

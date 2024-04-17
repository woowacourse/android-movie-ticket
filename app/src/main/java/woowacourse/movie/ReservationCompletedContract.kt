package woowacourse.movie

import woowacourse.movie.model.Ticket

interface ReservationCompletedContract {
    interface View {
        fun readTicketData(): Ticket?

        fun initializeTicketDetails(ticket: Ticket)
    }

    interface Presenter {
        fun onViewCreated()
    }
}

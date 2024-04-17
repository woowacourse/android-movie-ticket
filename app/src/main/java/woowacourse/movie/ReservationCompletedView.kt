package woowacourse.movie

interface ReservationCompletedView {
    fun readTicketData(): Ticket?

    fun initializeTicketDetails(ticket: Ticket)
}

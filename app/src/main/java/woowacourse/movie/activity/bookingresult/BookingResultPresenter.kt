package woowacourse.movie.activity.bookingresult

import woowacourse.movie.domain.Ticket

class BookingResultPresenter(
    private val view: BookingResultContract.View,
) : BookingResultContract.Presenter {
    override fun loadTicket(ticket: Ticket) {
        view.showTicketInfo(ticket)
    }
}

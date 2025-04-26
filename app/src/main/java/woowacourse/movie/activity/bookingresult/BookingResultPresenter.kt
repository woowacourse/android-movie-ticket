package woowacourse.movie.activity.bookingresult

import woowacourse.movie.domain.Ticket

class BookingResultPresenter : BookingResultContract.Presenter {
    private var view: BookingResultContract.View? = null

    override fun attachView(view: BookingResultContract.View) {
        this.view = view
    }

    override fun loadTicket(ticket: Ticket) {
        view?.showTicketInfo(ticket)
    }
}

package woowacourse.movie.presenter

import woowacourse.movie.contract.ReservationContract
import woowacourse.movie.model.Ticket
import woowacourse.movie.model.Ticket.Companion.TICKET_PRICE

class ReservationPresenter(private val view: ReservationContract.View) :
    ReservationContract.Presenter {
    var ticket: Ticket = Ticket()

    override fun subTicketCount() {
        ticket.subCount()
        view.updateTicketCount()
    }

    override fun addTicketCount() {
        ticket.addCount()
        view.updateTicketCount()
    }
}

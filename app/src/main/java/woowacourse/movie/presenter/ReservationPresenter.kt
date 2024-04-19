package woowacourse.movie.presenter

import android.content.Intent
import woowacourse.movie.contract.ReservationContract
import woowacourse.movie.model.Ticket
import woowacourse.movie.model.Ticket.Companion.TICKET_PRICE

class ReservationPresenter(private val view: ReservationContract.View) :
    ReservationContract.Presenter {
    private var ticket: Ticket = Ticket()

    override fun subTicketCount() {
        ticket.sub()
        view.updateTicketCount()
    }

    override fun addTicketCount() {
        ticket.add()
        view.updateTicketCount()
    }

    override fun clickReservationCompleteButton(
        intent: Intent,
        title: String,
        screenDate: String,
        count: String,
    ) {
        intent.putExtra("title", title)
        intent.putExtra("screenDate", screenDate)
        ticket = Ticket(count.toInt())
        intent.putExtra("count", ticketCount().toString())
        intent.putExtra("price", totalPrice())
    }

    override fun ticketCount(): Int = ticket.count()

    private fun totalPrice(): Int = TICKET_PRICE * ticket.count()
}

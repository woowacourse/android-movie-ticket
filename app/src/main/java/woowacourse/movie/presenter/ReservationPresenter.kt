package woowacourse.movie.presenter

import android.content.Intent
import woowacourse.movie.contract.ReservationContract
import woowacourse.movie.model.Payment
import woowacourse.movie.model.Ticket

class ReservationPresenter(private val view: ReservationContract.View) :
    ReservationContract.Presenter {
    private var ticket: Ticket = Ticket()
    private val payment: Payment = Payment()

    override fun subTicketCount() {
        ticket.sub()
        view.updateTicketCount()
    }

    override fun addTicketCount() {
        ticket.add()
        view.updateTicketCount()
    }

    override fun clickReservationCompleteButton(
        title: String,
        screenDate: String,
        intent: Intent,
    ) {
        intent.putExtra("title", title)
        intent.putExtra("screenDate", screenDate)
        intent.putExtra("count", ticketCount().toString())
        intent.putExtra("price", totalTicketPrice())
    }

    override fun ticketCount(): Int = ticket.count()

    override fun totalTicketPrice(): Int = payment.price(ticketCount())
}

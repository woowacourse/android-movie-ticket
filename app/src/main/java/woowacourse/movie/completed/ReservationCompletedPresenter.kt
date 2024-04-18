package woowacourse.movie.completed

import android.content.Intent
import android.os.Build
import woowacourse.movie.model.Ticket

class ReservationCompletedPresenter(private val view: ReservationCompletedContract.View) :
    ReservationCompletedContract.Presenter {
    override fun readTicketData(intent: Intent) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(EXTRA_TICKET, Ticket::class.java)
        } else {
            intent.getSerializableExtra(EXTRA_TICKET) as? Ticket
        }

    override fun onViewCreated(intent: Intent) {
        val ticket = readTicketData(intent) ?: return
        view.initializeTicketDetails(ticket)
    }

    companion object {
        const val EXTRA_TICKET = "ticket"
    }
}

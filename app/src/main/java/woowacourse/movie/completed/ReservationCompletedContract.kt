package woowacourse.movie.completed

import android.content.Intent
import woowacourse.movie.model.Ticket

interface ReservationCompletedContract {
    interface View {
        fun initializeTicketDetails(ticket: Ticket)
    }

    interface Presenter {
        fun onViewCreated(intent: Intent)

        fun readTicketData(intent: Intent): Ticket?
    }
}

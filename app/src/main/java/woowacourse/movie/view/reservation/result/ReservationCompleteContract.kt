package woowacourse.movie.view.reservation.result

import woowacourse.movie.domain.Seats
import woowacourse.movie.domain.Ticket

class ReservationCompleteContract {
    interface Presenter {
        fun fetchData(
            ticket: Ticket?,
            seats: Seats?,
        )
    }

    interface View {
        fun handleInvalidTicket()

        fun showTicketInfo(
            ticket: Ticket,
            seats: Seats,
        )

        fun showSeatsInfo(seats: String)

        fun showTicketMoney(moviePrice: Int)
    }
}

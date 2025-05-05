package woowacourse.movie.view.reservation.result

import woowacourse.movie.domain.Ticket
import woowacourse.movie.domain.movieseat.Seats

class ReservationCompleteContract {
    interface Presenter {
        fun fetchData(
            ticket: Ticket,
            seats: Seats,
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

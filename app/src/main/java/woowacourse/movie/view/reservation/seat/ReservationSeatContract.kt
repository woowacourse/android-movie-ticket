package woowacourse.movie.view.reservation.seat

import woowacourse.movie.domain.Position
import woowacourse.movie.domain.Ticket

interface ReservationSeatContract {
    interface Present {
        fun fetchData(ticket: Ticket?)

        fun updateMoney()

        fun addSeat(position: Position)

        fun removeSeat(position: Position)
    }

    interface View {
        fun handleInvalidTicket()

        fun setSeatTag()

        fun setSeatInit()

        fun showMovieName(movieName: String)

        fun showTicketMoney(moviePrice: Int)

        fun setSeatClickListener()
    }
}

package woowacourse.movie.view.reservation.seat

import woowacourse.movie.domain.Ticket

interface ReservationSeatContract {
    interface Present {
        fun fetchData(ticket: Ticket?)

        fun updateMoney()
    }

    interface View {
        fun handleInvalidTicket()

        fun setSeatTag()

        fun setSeatInit()

        fun showMovieName(movieName: String)

        fun showTicketMoney(moviePrice: Int)
    }
}

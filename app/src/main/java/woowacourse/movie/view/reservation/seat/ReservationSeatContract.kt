package woowacourse.movie.view.reservation.seat

import woowacourse.movie.domain.Ticket

interface ReservationSeatContract {
    interface Present {
        fun fetchData(ticket: Ticket?)
    }

    interface View {
        fun handleInvalidTicket()

        fun showReservationSeatScreen()

        fun setSeatTag()

        fun setSeatNumber()
    }
}

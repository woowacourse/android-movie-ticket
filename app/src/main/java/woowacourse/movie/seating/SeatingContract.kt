package woowacourse.movie.seating

import woowacourse.movie.domain.ReservationInfo
import woowacourse.movie.domain.Ticket

interface SeatingContract {
    interface Presenter {
        fun set(reservationInfo: ReservationInfo)

        fun clickedSeat(seat: String)
    }

    interface View {
        fun showTitle(title: String)

        fun showPrice(price: String)

        fun showActivateButton(ticket: Ticket)

        fun showDeactivateButton()

        fun showActivateSeat()

        fun showDeactivateSeat(selectedSeats: MutableSet<String>)
    }
}

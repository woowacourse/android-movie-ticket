package woowacourse.movie.view.reservation.seat

import woowacourse.movie.domain.Position
import woowacourse.movie.domain.Seats
import woowacourse.movie.domain.Ticket

interface ReservationSeatContract {
    interface Present {
        fun fetchData(ticket: Ticket?)

        fun updateMoney()

        fun selectSeat(position: Position)

        fun deselectSeat(position: Position)
    }

    interface View {
        fun handleInvalidTicket()

        fun setSeatTag()

        fun setSeatInit()

        fun showMovieName(movieName: String)

        fun showTicketMoney(moviePrice: Int)

        fun setSeatClickListener()

        fun selectSeatView(position: Position)

        fun deselectSeatView(position: Position)

        fun navigateToReservationComplete(
            ticket: Ticket,
            seats: Seats,
        )

        fun showReservationDialog(
            ticket: Ticket,
            seats: Seats,
        )

        fun selectableButton()

        fun deSelectableButton()

        fun setReservationButton(onClickConfirm: () -> Unit)
    }
}

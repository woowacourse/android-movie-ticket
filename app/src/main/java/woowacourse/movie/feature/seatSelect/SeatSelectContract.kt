package woowacourse.movie.feature.seatSelect

import woowacourse.movie.feature.ticket.TicketData
import woowacourse.movie.model.ticket.seat.Seat

interface SeatSelectContract {
    interface View {
        fun setMovieTitle(movieTitle: String)

        fun initSeatClickListener()

        fun seatSelect(seat: Seat)

        fun seatUnSelect(seat: Seat)

        fun setTicketPrice(ticketPrice: Int)

        fun printError(message: SeatSelectErrorType)

        fun navigateToTicketView(ticketData: TicketData)

        fun setSubmitButtonView(isMaximumSelectedSeat: Boolean)
    }

    interface Presenter {
        fun initSelectSeatView()

        fun onSeatInput(seat: Seat)

        fun getSelectedSeatsData(): SeatsData

        fun setSeatsData(seatsData: SeatsData)

        fun handleCompleteSelectSeat()
    }
}

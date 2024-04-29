package woowacourse.movie.presenter.contract

import woowacourse.movie.model.theater.SeatClass
import woowacourse.movie.model.theater.TheaterSize
import woowacourse.movie.model.ticketing.BookingSeat
import woowacourse.movie.view.state.TicketingForm
import woowacourse.movie.view.state.TicketingResult

interface SeatSelectionContract {
    interface View {
        fun initializeSeatTable(
            theaterSize: TheaterSize,
            rowClassInfo: Map<Int, SeatClass>,
            movieTitle: kotlin.String,
            totalPrice: Int,
            selectedSeats: List<BookingSeat>,
        )

        fun toggleSeat(
            row: Int,
            column: Int,
            seatClass: SeatClass,
            isSelected: Boolean,
            columnSize: Int,
        )

        fun updateTotalPrice(totalPrice: Int)

        fun updateButtonStatus(isAvailable: Boolean)

        fun navigateToResultScreen(ticketingResult: TicketingResult)

        fun showToastMessage(message: String)
    }

    interface Presenter {
        fun loadSeats(
            ticketingForm: TicketingForm,
            seats: List<BookingSeat>,
        )

        fun makeReservation()

        fun updateSeat(
            row: Int,
            column: Int,
            seatClass: SeatClass,
            columnSize: Int,
        )
    }
}

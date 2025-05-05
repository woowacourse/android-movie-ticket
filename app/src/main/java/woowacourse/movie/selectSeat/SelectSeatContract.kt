package woowacourse.movie.selectSeat

import woowacourse.movie.model.Seat
import woowacourse.movie.uiModel.TicketUIModel

interface SelectSeatContract {
    interface View {
        fun askToConfirmBook()

        fun highlightSeat(tag: String)

        fun unHighlightSeat(tag: String)

        fun showPrice(money: Int)

        fun showFullSeat()

        fun navigateToBookingResult(ticketUIModel: TicketUIModel)

        fun enableButton()

        fun unableButton()
    }

    interface Presenter {
        fun loadSeats(ticketUIModel: TicketUIModel)

        fun toggleSeat(
            seat: Seat,
            fullCount: Int,
        )

        fun askConfirm()

        fun completeBooking()
    }
}

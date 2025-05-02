package woowacourse.movie.selectSeat

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
        fun init(ticketUIModel: TicketUIModel)

        fun toggleSeat(
            tag: String,
            fullCount: Int,
        )

        fun askConfirm()

        fun completeBooking()
    }
}

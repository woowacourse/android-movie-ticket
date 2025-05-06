package woowacourse.movie.selectSeat

import woowacourse.movie.model.Seat
import woowacourse.movie.model.SeatSelection
import woowacourse.movie.uiModel.TicketUIModel

class SelectSeatPresenter(
    private var view: SelectSeatContract.View,
) : SelectSeatContract.Presenter {
    private lateinit var seatSelection: SeatSelection

    override fun loadSeats(ticketUIModel: TicketUIModel) {
        seatSelection = SeatSelection.fromUIModel(ticketUIModel)
        view.showPrice(0)
        view.unableButton()
    }

    override fun toggleSeat(
        seat: Seat,
        fullCount: Int,
    ) {
        val isSelected = seatSelection.isSeatSelected(seat)
        val isFull = seatSelection.isFull(fullCount)

        if (isSelected) {
            seatSelection.unSelectSeat(seat)
            view.unHighlightSeat(seat.tag)
        } else if (isFull) {
            view.showFullSeat()
            return
        } else {
            seatSelection.selectSeat(seat)
            view.highlightSeat(seat.tag)
        }

        view.showPrice(seatSelection.getTotalMoney())

        if (seatSelection.isFull(fullCount)) {
            view.enableButton()
        } else {
            view.unableButton()
        }
    }

    override fun askConfirm() {
        view.askToConfirmBook()
    }

    override fun completeBooking() {
        val ticket = seatSelection
        val uiModel = TicketUIModel.from(ticket)
        view.navigateToBookingResult(uiModel)
    }
}

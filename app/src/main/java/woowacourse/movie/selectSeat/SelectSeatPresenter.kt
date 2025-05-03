package woowacourse.movie.selectSeat

import woowacourse.movie.model.SelectSeats
import woowacourse.movie.uiModel.TicketUIModel

class SelectSeatPresenter(
    var view: SelectSeatContract.View,
) : SelectSeatContract.Presenter {
    val selectSeat = SelectSeats()

    override fun loadSeats(ticketUIModel: TicketUIModel) {
        view.showPrice(0)
        view.unableButton()
        selectSeat.setTicket(ticketUIModel)
    }

    override fun toggleSeat(
        tag: String,
        fullCount: Int,
    ) {
        val isSelected = selectSeat.isSeatSelected(tag)
        val isFull = selectSeat.isFullSeat(fullCount)

        if (isSelected) {
            selectSeat.unSelectSeat(tag)
            view.unHighlightSeat(tag)
            view.showPrice(selectSeat.ticket.money)
        } else if (isFull) {
            view.showFullSeat()
            return
        } else {
            selectSeat.selectSeat(tag)
            view.highlightSeat(tag)
            view.showPrice(selectSeat.ticket.money)
        }

        if (selectSeat.isFullSeat(fullCount)) {
            view.enableButton()
        } else {
            view.unableButton()
        }
    }

    override fun askConfirm() {
        view.askToConfirmBook()
    }

    override fun completeBooking() {
        view.navigateToBookingResult(selectSeat.ticket.toUIModel())
    }
}

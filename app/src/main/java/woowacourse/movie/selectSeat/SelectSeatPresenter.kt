package woowacourse.movie.selectSeat

import woowacourse.movie.model.SelectSeats
import woowacourse.movie.uiModel.TicketUIModel

class SelectSeatPresenter(
    var view: SelectSeatContract.View,
) : SelectSeatContract.Presenter {
    val selectSeat = SelectSeats()

    override fun init(ticketUIModel: TicketUIModel) {
        view.showPrice(0)
        view.disActiveButton()
        selectSeat.setTicket(ticketUIModel)
    }

    override fun toggleSeat(
        tag: String,
        fullCount: Int,
    ) {
        if (selectSeat.isSeatSelected(tag)) {
            selectSeat.unSelectSeat(tag)
            view.unHighlightSeat(tag)
            view.showPrice(selectSeat.ticket.money)
        } else {
            if (selectSeat.isFullSeat(fullCount)) {
                view.showFullSeat()
            } else {
                selectSeat.selectSeat(tag)
                view.highlightSeat(tag)
                view.showPrice(selectSeat.ticket.money)
            }
        }
        if (selectSeat.isFullSeat(fullCount)) {
            view.activeButton()
        } else {
            view.disActiveButton()
        }
    }

    override fun askConfirm() {
        view.askToConfirmBook()
    }

    override fun completeBooking() {
        view.navigateToBookingResult(selectSeat.ticket.toUIModel())
    }
}

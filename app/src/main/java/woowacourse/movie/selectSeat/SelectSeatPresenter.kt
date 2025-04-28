package woowacourse.movie.selectSeat

import woowacourse.movie.uiModel.TicketUIModel

class SelectSeatPresenter(
    var view: SelectSeatContract.View,
) : SelectSeatContract.Presenter {
    val selectSeat = SelectSeats()

    override fun onViewCreated(ticketUIModel: TicketUIModel) {
        view.setTitle(ticketUIModel)
        view.setMoney(ticketUIModel.money)
        view.setSeatClicker()
        view.setButton()

        selectSeat.setTicket(ticketUIModel)
    }

    override fun onSeatClicked(
        tag: String,
        fullCount: Int,
    ) {
        if (selectSeat.isSeatSelected(tag)) {
            selectSeat.unSelectSeat(tag)
            view.onSeatUnSelected(tag)
            view.setMoney(selectSeat.ticket.money)
        } else {
            if (selectSeat.isFullSeat(fullCount)) {
                view.showFullSeat()
            } else {
                selectSeat.selectSeat(tag)
                view.onSeatSelected(tag)
                view.setMoney(selectSeat.ticket.money)
            }
        }
    }

    override fun onBookButtonClicked() {
        view.changeView(selectSeat.ticket.toUIModel())
    }

    override fun onYesClick() {
        TODO("Not yet implemented")
    }
}

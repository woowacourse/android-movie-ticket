package woowacourse.movie.selectSeat

import woowacourse.movie.uiModel.Ticket

class SelectSeatPresenter(
    var view: SelectSeatContract.View,
) : SelectSeatContract.Presenter {
    val selectSeat = SelectSeats()

    override fun onViewCreated(ticket: Ticket) {
        view.setTitle(ticket)
        view.setMoney(ticket.money)
        view.setSeatClicker()
        view.setButton()

        selectSeat.setTicket(ticket)
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
        view.changeView(selectSeat.ticket.toDto())
    }

    override fun onYesClick() {
        TODO("Not yet implemented")
    }
}

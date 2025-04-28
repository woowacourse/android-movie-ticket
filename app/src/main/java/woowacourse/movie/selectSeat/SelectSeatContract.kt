package woowacourse.movie.selectSeat

import woowacourse.movie.uiModel.TicketUIModel

interface SelectSeatContract {
    interface View {
        fun setSeatClicker()

        fun setButton()

        fun askToConfirmBook()

        fun onSeatSelected(tag: String)

        fun onSeatUnSelected(tag: String)

        fun setTitle(ticketUIModel: TicketUIModel)

        fun setMoney(money: Int)

        fun showFullSeat()

        fun changeView(ticketUIModel: TicketUIModel)
    }

    interface Presenter {
        fun onViewCreated(ticketUIModel: TicketUIModel)

        fun onSeatClicked(
            tag: String,
            fullCount: Int,
        )

        fun onBookButtonClicked()

        fun onYesClick()
    }
}

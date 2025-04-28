package woowacourse.movie.selectSeat

import woowacourse.movie.uiModel.Ticket

interface SelectSeatContract {
    interface View {
        fun setSeatClicker()

        fun setButton()

        fun askToConfirmBook()

        fun onSeatSelected(tag: String)

        fun onSeatUnSelected(tag: String)

        fun setTitle(ticket: Ticket)

        fun setMoney(money: Int)

        fun showFullSeat()

        fun changeView(ticket: Ticket)
    }

    interface Presenter {
        fun onViewCreated(ticket: Ticket)

        fun onSeatClicked(
            tag: String,
            fullCount: Int,
        )

        fun onBookButtonClicked()

        fun onYesClick()
    }
}

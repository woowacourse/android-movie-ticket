package woowacourse.movie.presenter

import woowacourse.movie.model.Ticket

interface DetailContract {
    interface View {
        fun updateCounter(count: Int)
        fun startTicketActivity(ticket: Ticket)
    }

    interface Presenter {
        fun onMinusButtonClicked(count: Int)
        fun onPlusButtonClicked(count: Int)
        fun onReserveButtonClicked(ticket: Ticket)
    }
}

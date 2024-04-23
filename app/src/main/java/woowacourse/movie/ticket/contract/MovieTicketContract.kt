package woowacourse.movie.ticket.contract

import woowacourse.movie.ticket.model.Ticket
import woowacourse.movie.ticket.presenter.MovieTicketPresenter

interface MovieTicketContract {
    interface View {
        val presenter: MovieTicketPresenter

        fun showTicketInfo(info: Ticket)
    }

    interface Presenter {
        val ticket: Ticket

        fun setTicketInfo()
    }
}

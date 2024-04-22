package woowacourse.movie.contract

import woowacourse.movie.model.Ticket
import woowacourse.movie.presenter.MovieTicketPresenter

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

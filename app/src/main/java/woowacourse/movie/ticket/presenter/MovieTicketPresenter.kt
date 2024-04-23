package woowacourse.movie.ticket.presenter

import woowacourse.movie.ticket.contract.MovieTicketContract
import woowacourse.movie.ticket.model.TicketDataResource

class MovieTicketPresenter(
    val view: MovieTicketContract.View,
) : MovieTicketContract.Presenter {
    override val ticket = TicketDataResource.ticket

    override fun setTicketInfo() {
        view.showTicketInfo(ticket)
    }
}

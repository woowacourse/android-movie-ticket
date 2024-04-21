package woowacourse.movie.presenter

import woowacourse.movie.contract.MovieTicketContract
import woowacourse.movie.model.TicketDataResource

class MovieTicketPresenter(
    val view: MovieTicketContract.View,
) : MovieTicketContract.Presenter {
    override val ticket = TicketDataResource.ticket

    override fun setTicketInfo() {
        view.showTicketInfo(ticket)
    }
}

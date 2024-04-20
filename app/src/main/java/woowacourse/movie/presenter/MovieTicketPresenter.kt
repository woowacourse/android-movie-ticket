package woowacourse.movie.presenter

import woowacourse.movie.contract.MovieTicketContract
import woowacourse.movie.model.TicketData

class MovieTicketPresenter(
    val view: MovieTicketContract.View,
) : MovieTicketContract.Presenter {
    override val ticket = TicketData.ticket

    override fun setTicketInfo() {
        view.showTicketInfo(ticket)
    }
}

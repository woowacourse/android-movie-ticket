package woowacourse.movie.presentation.detail

import woowacourse.movie.presentation.reservation.model.TicketModel

class MovieDetailPresenter(
    private val view: MovieDetailContract.View,
) : MovieDetailContract.Presenter {
    override fun loadTicket(ticketModel: TicketModel?) {
        view.showTicket(ticketModel)
    }
}

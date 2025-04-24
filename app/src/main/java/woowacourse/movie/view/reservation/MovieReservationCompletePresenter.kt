package woowacourse.movie.view.reservation

import woowacourse.movie.view.reservation.ticket.TicketUiModel
import woowacourse.movie.view.reservation.ticket.toDomain

class MovieReservationCompletePresenter(
    private val view: MovieReservationCompleteContract.View,
    private val ticket: TicketUiModel,
) : MovieReservationCompleteContract.Presenter {
    override fun loadMovieReservationCompleteScreen() {
        view.showTicketInfo(ticket)
        view.showTotalPrice(ticket.toDomain().totalPrice())
    }
}

package woowacourse.movie.ui.complete

import woowacourse.movie.model.data.UserTickets

class MovieReservationCompletePresenter(
    private val view: MovieReservationCompleteContract.View,
    private val userTickets: UserTickets,
) :
    MovieReservationCompleteContract.Presenter {
    override fun loadTicket(ticketId: Long) {
        try {
            val userTicket = userTickets.find(ticketId)
            view.showTicket(userTicket)
        } catch (e: NoSuchElementException) {
            view.showError(e)
        }
    }

    override fun handleError(throwable: Throwable) {
        view.showError(throwable)
    }
}

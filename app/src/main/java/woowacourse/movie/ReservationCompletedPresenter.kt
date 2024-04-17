package woowacourse.movie

class ReservationCompletedPresenter(private val view: ReservationCompletedView) {
    fun onViewCreated() {
        val ticket = view.readTicketData() ?: return
        view.initializeTicketDetails(ticket)
    }
}

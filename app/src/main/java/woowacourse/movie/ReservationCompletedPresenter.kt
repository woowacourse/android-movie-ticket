package woowacourse.movie

class ReservationCompletedPresenter(private val view: ReservationCompletedContract.View) :
    ReservationCompletedContract.Presenter {
    override fun onViewCreated() {
        val ticket = view.readTicketData() ?: return
        view.initializeTicketDetails(ticket)
    }
}

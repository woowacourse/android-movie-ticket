package woowacourse.movie.completed

class ReservationCompletedPresenter(private val view: ReservationCompletedContract.View) :
    ReservationCompletedContract.Presenter {
    override fun onViewCreated() {
        val reservation = view.readTicketData() ?: return
        view.initializeTicketDetails(reservation)
    }
}

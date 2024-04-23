package woowacourse.movie.screen.completed

import woowacourse.movie.model.Reservation

class ReservationCompletedPresenter(private val view: ReservationCompletedContract.View) :
    ReservationCompletedContract.Presenter {
    override fun fetchReservationDetails(reservation: Reservation) {
        view.initializeReservationDetails(reservation)
    }
}

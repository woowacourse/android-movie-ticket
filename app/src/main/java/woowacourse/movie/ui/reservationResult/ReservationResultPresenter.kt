package woowacourse.movie.ui.reservationResult

import woowacourse.movie.domain.model.reservation.Reservation

class ReservationResultPresenter(private val view: ReservationResultContract.View) :
    ReservationResultContract.Presenter {
    private lateinit var reservation: Reservation

    override fun initScreen(reservation: Reservation) {
        this.reservation = reservation
        view.initScreen(this.reservation)
    }
}

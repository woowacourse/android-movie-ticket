package woowacourse.movie.result

import woowacourse.movie.domain.Reservation

class ReservationResultPresenter(
    private val view: ReservationResultContract.View,
) : ReservationResultContract.Presenter {
    private lateinit var reservation: Reservation

    override fun setUpReservation(reservation: Reservation) {
        this.reservation = reservation
    }

    override fun showReservation() {
        view.bindReservation(reservation)
        view.bindTicket(reservation.points.points)
        view.bindTotalPrice(reservation.points.totalPrice())
    }
}

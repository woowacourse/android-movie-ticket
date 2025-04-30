package woowacourse.movie.result

import woowacourse.movie.domain.Reservation

class ReservationResultPresenter(
    private val view: ReservationResultContract.View,
) : ReservationResultContract.Presenter {
    private lateinit var reservation: Reservation

    override fun initReservation(reservation: Reservation) {
        this.reservation = reservation

        view.showReservation(reservation)
        view.showTicket(reservation.seats.seats)
        view.showTotalPrice(reservation.seats.totalPrice())
    }
}

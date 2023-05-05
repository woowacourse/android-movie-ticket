package woowacourse.movie.view.activities.reservationresult

import woowacourse.movie.domain.screening.Reservation
import woowacourse.movie.repository.ReservationRepository

class ReservationResultPresenter(private val view: ReservationResultContract.View, private val reservationId: Long) :
    ReservationResultContract.Presenter {

    private lateinit var reservation: Reservation

    override fun loadReservation() {
        reservation = ReservationRepository.findById(reservationId) ?: return
        view.setReservation(ReservationUIState.from(reservation))
    }
}

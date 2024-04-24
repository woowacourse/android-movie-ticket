package woowacourse.movie.presenter

import woowacourse.movie.contract.PurchaseConfirmationContract
import woowacourse.movie.model.Reservation
import woowacourse.movie.model.screening.Screening
import woowacourse.movie.repository.PseudoReservationRepository
import woowacourse.movie.repository.ReservationRepository

class PurchaseConfirmationPresenter(
    private val view: PurchaseConfirmationContract.View,
    private val reservationRepository: ReservationRepository = PseudoReservationRepository(),
) : PurchaseConfirmationContract.Presenter {
    override fun loadReservation(reservationId: Int) {
        val reservation = reservationRepository.getReservation(reservationId) ?: Reservation(Screening.default, 0)
        view.displayReservation(reservation)
    }
}

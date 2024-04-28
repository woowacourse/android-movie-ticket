package woowacourse.movie.presenter

import woowacourse.movie.contract.PurchaseConfirmationContract
import woowacourse.movie.repository.PseudoReservationRepository
import woowacourse.movie.repository.ReservationRepository

class PurchaseConfirmationPresenter(
    private val view: PurchaseConfirmationContract.View,
    private val reservationRepository: ReservationRepository = PseudoReservationRepository(),
) : PurchaseConfirmationContract.Presenter {
    override fun loadReservation(reservationId: Int) {
        val reservation =
            reservationRepository.getLastReservation() ?: throw IllegalStateException(
                GET_LAST_RESERVATION_ERROR,
            )
        view.displayReservation(reservation)
    }

    companion object {
        private const val GET_LAST_RESERVATION_ERROR = "예매 확인 오류. 미지막 예매 정보를 가져오는 데 실패했습니다."
    }
}

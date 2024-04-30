package woowacourse.movie.presentation.purchase_confirmation

import woowacourse.movie.repository.movie.MovieRepository
import woowacourse.movie.repository.movie.PseudoMovieRepository
import woowacourse.movie.repository.reservation.PseudoReservationRepository
import woowacourse.movie.repository.reservation.ReservationRepository
import woowacourse.movie.uimodel.reservation.toReservationBrief

class PurchaseConfirmationPresenter(
    private val view: PurchaseConfirmationContract.View,
    private val reservationRepository: ReservationRepository = PseudoReservationRepository(),
    private val movieRepository: MovieRepository = PseudoMovieRepository(),
) : PurchaseConfirmationContract.Presenter {
    override fun loadReservation() {
        val reservation =
            reservationRepository.getLastReservation() ?: throw IllegalStateException(
                GET_LAST_RESERVATION_ERROR,
            )
        view.displayReservation(reservation.toReservationBrief(movieRepository))
    }

    companion object {
        private const val GET_LAST_RESERVATION_ERROR = "예매 확인 오류. 미지막 예매 정보를 가져오는 데 실패했습니다."
    }
}

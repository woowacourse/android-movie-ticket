package woowacourse.movie.view.reservation.result

import woowacourse.movie.domain.model.ReservationInfo

class ReservationResultPresenter(
    private var view: ReservationResultContract.View,
) : ReservationResultContract.Presenter {
    override fun loadReservationInfo(reservationInfo: ReservationInfo?) {
        reservationInfo?.let {
            view.showReservationResult(reservationInfo)
            return
        }
        // 오류 처리
        if (reservationInfo == null) throw IllegalArgumentException("받아올 값이 없습니다")
    }
}

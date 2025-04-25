package woowacourse.movie.view.reservation.result

import woowacourse.movie.domain.model.ReservationInfo

interface ReservationResultContract {
    interface View {
        fun showReservationResult(reservationInfo: ReservationInfo)
    }

    interface Presenter {
        fun loadReservationInfo(reservationInfo: ReservationInfo?)
    }
}

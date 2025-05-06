package woowacourse.movie.view.reservation.result

import woowacourse.movie.domain.model.ReservationInfo

interface ReservationResultContract {
    interface View {
        fun showReservationResult(reservationInfo: ReservationInfo)

        fun showMessage(message: String)
    }

    interface Presenter {
        fun loadReservationInfo(reservationInfo: ReservationInfo?)
    }
}

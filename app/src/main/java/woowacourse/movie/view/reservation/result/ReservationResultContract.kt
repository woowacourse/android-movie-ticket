package woowacourse.movie.view.reservation.result

import woowacourse.movie.domain.model.ReservationInfo

interface ReservationResultContract {
    interface View {
        fun showReservationResult(
            title: String,
            dateTime: String,
            count: String,
            seats: String,
            totalPrice: String,
        )

        fun showMessage(message: String)
    }

    interface Presenter {
        fun loadReservationInfo(reservationInfo: ReservationInfo?)
    }
}

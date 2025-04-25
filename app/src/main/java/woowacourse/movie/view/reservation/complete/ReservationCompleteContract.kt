package woowacourse.movie.view.reservation.complete

import woowacourse.movie.model.ReservationInfo

interface ReservationCompleteContract {
    interface View {
        fun showErrorDialog()

        fun updateReservationInfo(reservationInfo: ReservationInfo)
    }

    interface Presenter {
        fun fetchData(getReservationInfo: () -> ReservationInfo?)
    }
}

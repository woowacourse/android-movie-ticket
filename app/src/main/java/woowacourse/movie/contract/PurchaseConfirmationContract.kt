package woowacourse.movie.contract

import woowacourse.movie.uiModels.reservation.ReservationBrief

interface PurchaseConfirmationContract {
    interface View {
        fun displayReservation(reservationBrief: ReservationBrief)
    }

    interface Presenter {
        fun loadReservation()
    }
}

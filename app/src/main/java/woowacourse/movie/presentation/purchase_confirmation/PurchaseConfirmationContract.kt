package woowacourse.movie.presentation.purchase_confirmation

import woowacourse.movie.uimodel.reservation.ReservationBrief

interface PurchaseConfirmationContract {
    interface View {
        fun displayReservation(reservationBrief: ReservationBrief)
    }

    interface Presenter {
        fun loadReservation()
    }
}

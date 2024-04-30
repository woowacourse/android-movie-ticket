package woowacourse.movie.contract

import woowacourse.movie.ui.ReservationBrief

interface PurchaseConfirmationContract {
    interface View {
        fun displayReservation(reservationBrief: ReservationBrief)
    }

    interface Presenter {
        fun loadReservation()
    }
}

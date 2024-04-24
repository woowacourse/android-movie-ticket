package woowacourse.movie.contract

import woowacourse.movie.model.Reservation

interface PurchaseConfirmationContract {
    interface View {
        fun displayReservation(reservation: Reservation)
    }

    interface Presenter {
        fun loadReservation(reservationId: Int)
    }
}

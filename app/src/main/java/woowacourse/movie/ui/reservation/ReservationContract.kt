package woowacourse.movie.ui.reservation

import woowacourse.movie.domain.model.Reservation

interface ReservationContract {
    interface View {
        fun showReservation(reservation: Reservation)

        fun showToastMessage(message: String)

        fun showSnackBar(message: String)

        fun goToBack(message: String)

        fun unexpectedFinish(message: String)
    }

    interface Presenter {
        fun loadReservation(id: Int)

        fun loadReservation2(reservation2Id: Int)
    }
}

package woowacourse.movie.ui.reservation

import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.domain.model.Reservation2

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
    }
}

interface ReservationContract2 {
    interface View {
        fun showReservation(reservation: Reservation2)

        fun showToastMessage(message: String)

        fun showSnackBar(message: String)

        fun goToBack(message: String)

        fun unexpectedFinish(message: String)
    }

    interface Presenter {
        fun loadReservation(id: Int)
    }
}

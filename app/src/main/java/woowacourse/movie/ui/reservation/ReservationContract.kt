package woowacourse.movie.ui.reservation

import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.domain.model.Reservation2

interface ReservationContract {
    interface View {
        fun showReservation2(reservation2: Reservation2)

        fun showToastMessage(message: String)

        fun showSnackBar(message: String)

        fun goToBack(message: String)

        fun unexpectedFinish(message: String)
    }

    interface Presenter {
        fun loadReservation2(reservation2Id: Int)
    }
}

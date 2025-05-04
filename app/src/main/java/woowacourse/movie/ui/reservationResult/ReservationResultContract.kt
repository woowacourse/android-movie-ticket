package woowacourse.movie.ui.reservationResult

import woowacourse.movie.domain.model.reservation.Reservation

interface ReservationResultContract {
    interface Presenter {
        fun initReservation(reservation: Reservation)
    }

    interface View {
        fun initScreen(reservation: Reservation)
    }
}

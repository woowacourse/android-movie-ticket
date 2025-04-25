package woowacourse.movie.ui.reservationResult

import woowacourse.movie.domain.model.Reservation

interface ReservationResultContract {
    interface Presenter {
        fun initScreen(reservation: Reservation)
    }

    interface View {
        fun initScreen(reservation: Reservation)
    }
}

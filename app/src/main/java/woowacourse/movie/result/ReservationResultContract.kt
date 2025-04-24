package woowacourse.movie.result

import woowacourse.movie.domain.Reservation

interface ReservationResultContract {
    interface View {
        fun bindReservation(reservation: Reservation)
    }

    interface Presenter {
        fun setUpReservation(reservation: Reservation)

        fun showReservation()
    }
}

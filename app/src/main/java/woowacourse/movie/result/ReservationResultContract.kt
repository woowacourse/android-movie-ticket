package woowacourse.movie.result

import woowacourse.movie.domain.Reservation

interface ReservationResultContract {
    interface View {
        fun bindReservation(reservation: Reservation)

        fun bindTotalPrice(price: Int)
    }

    interface Presenter {
        fun setUpReservation(reservation: Reservation)

        fun showReservation()
    }
}

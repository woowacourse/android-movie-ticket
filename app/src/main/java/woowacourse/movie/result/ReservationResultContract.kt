package woowacourse.movie.result

import woowacourse.movie.domain.Reservation
import woowacourse.movie.domain.Seat

interface ReservationResultContract {
    interface View {
        fun bindReservation(reservation: Reservation)

        fun bindTotalPrice(price: Int)

        fun bindTicket(seats: Set<Seat>)
    }

    interface Presenter {
        fun setUpReservation(reservation: Reservation)

        fun showReservation()
    }
}

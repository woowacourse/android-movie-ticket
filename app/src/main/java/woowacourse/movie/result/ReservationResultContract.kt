package woowacourse.movie.result

import woowacourse.movie.domain.Reservation
import woowacourse.movie.domain.Seat

interface ReservationResultContract {
    interface View {
        fun showReservation(reservation: Reservation)

        fun showTotalPrice(price: Int)

        fun showTicket(seats: Set<Seat>)
    }

    interface Presenter {
        fun initReservation(reservation: Reservation)
    }
}

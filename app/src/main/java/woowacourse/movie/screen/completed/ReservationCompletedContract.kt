package woowacourse.movie.screen.completed

import woowacourse.movie.model.Reservation

interface ReservationCompletedContract {
    interface View {
        fun readTicketData(): Reservation?

        fun initializeTicketDetails(reservation: Reservation)
    }

    interface Presenter {
        fun onStart()
    }
}

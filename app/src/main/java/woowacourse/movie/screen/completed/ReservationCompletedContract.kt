package woowacourse.movie.screen.completed

import woowacourse.movie.model.Reservation

interface ReservationCompletedContract {
    interface View {
        fun initializeReservationDetails(reservation: Reservation)
    }

    interface Presenter {
        fun fetchReservationDetails(id: Long)
    }
}

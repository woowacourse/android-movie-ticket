package woowacourse.movie.screen.completed

import woowacourse.movie.screen.reservation.ReservationModel

interface ReservationCompletedContract {
    interface View {
        fun initializeReservationDetails(reservation: ReservationModel)
    }

    interface Presenter {
        fun fetchReservationDetails(id: Long)
    }
}

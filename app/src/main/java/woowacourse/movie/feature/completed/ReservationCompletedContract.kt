package woowacourse.movie.feature.completed

import woowacourse.movie.feature.reservation.ui.ReservationModel

interface ReservationCompletedContract {
    interface View {
        fun initializeReservationDetails(reservation: ReservationModel)
    }

    interface Presenter {
        fun fetchReservationDetails(id: Long)
    }
}

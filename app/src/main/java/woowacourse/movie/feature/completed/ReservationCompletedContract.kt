package woowacourse.movie.feature.completed

import woowacourse.movie.feature.reservation.ui.TicketModel

interface ReservationCompletedContract {
    interface View {
        fun initializeReservationDetails(ticket: TicketModel)
    }

    interface Presenter {
        fun fetchReservationDetails(id: Long)
    }
}

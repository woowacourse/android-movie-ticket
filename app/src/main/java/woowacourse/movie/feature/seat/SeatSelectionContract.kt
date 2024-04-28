package woowacourse.movie.feature.seat

import woowacourse.movie.feature.main.ui.ScreeningModel
import woowacourse.movie.feature.reservation.ui.SeatModel

interface SeatSelectionContract {
    interface View {
        fun initialize(
            movie: ScreeningModel,
            seats: List<SeatModel>,
        )

        fun navigateToReservationCompleted(reservationId: Long)
    }

    interface Presenter {
        fun fetchData()
    }
}

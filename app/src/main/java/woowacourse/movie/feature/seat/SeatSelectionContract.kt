package woowacourse.movie.feature.seat

import woowacourse.movie.feature.main.ui.ScreeningModel

interface SeatSelectionContract {
    interface View {
        fun initialize(movie: ScreeningModel)

        fun navigateToReservationCompleted(reservationId: Long)
    }

    interface Presenter {
        fun fetchData()
    }
}

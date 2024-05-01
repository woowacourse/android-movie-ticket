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

        fun noticeReservationImpossible(quantity: Int)

        fun updatePriceTextView(price: Long)

        fun checkSeatSelected(index: Int)

        fun confirmReservation()
    }

    interface Presenter {
        fun fetchData()

        fun saveTicket()

        fun proceedReservation()

        fun proceedSeatSelection(index: Int)
    }
}

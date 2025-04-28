package woowacourse.movie.feature.bookingseat.contract

import woowacourse.movie.feature.model.BookingInfoUiModel
import woowacourse.movie.feature.model.MovieSeatUiModel
import woowacourse.movie.feature.model.SeatSelectionUiState

interface BookingSeatContract {
    interface View {
        fun showSeats()

        fun showBookingInfo(bookingInfo: BookingInfoUiModel)

        fun showBookingCompleteDialog()

        fun updatePrice(price: Int)

        fun updateSeatSelectionCompleteButton(enabled: Boolean)

        fun navigateToBookingComplete(bookingInfo: BookingInfoUiModel)

        fun navigateToBack()
    }

    interface Presenter {
        fun prepareBookingInfo(bookingInfo: BookingInfoUiModel)

        fun prepareSeats(
            row: Int,
            column: Int,
        ): MovieSeatUiModel

        fun selectSeat(seat: MovieSeatUiModel): SeatSelectionUiState

        fun completeSeatSelection()

        fun confirmSeatSelection()

        fun cancelSeatSelection()
    }
}

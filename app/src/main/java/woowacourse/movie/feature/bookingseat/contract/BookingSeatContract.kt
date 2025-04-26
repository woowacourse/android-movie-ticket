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
        fun onCreateView(bookingInfo: BookingInfoUiModel)

        fun onSeatSetup(
            row: Int,
            column: Int,
        ): MovieSeatUiModel

        fun onSeatClicked(seat: MovieSeatUiModel): SeatSelectionUiState

        fun onSeatSelectionCompleteClicked()

        fun onSeatSelectionCompleteConfirmed()

        fun onBackButtonClicked()
    }
}

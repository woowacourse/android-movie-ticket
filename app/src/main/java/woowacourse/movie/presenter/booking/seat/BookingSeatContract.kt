package woowacourse.movie.presenter.booking.seat

import androidx.annotation.StringRes
import woowacourse.movie.ui.model.booking.BookingResultUiModel
import woowacourse.movie.ui.model.seat.SeatsUiModel

interface BookingSeatContract {
    interface Presenter {
        fun loadBookingResult(bookingResultUiModelOrNull: BookingResultUiModel?)

        fun loadInfos()

        fun updateSeat(seatPosition: String)

        fun updatePrice()

        fun proceedToBookingResult()
    }

    interface View {
        fun showSeatView(
            seatPosition: String,
            isSelected: Boolean,
        )

        fun showErrorMessage(
            @StringRes messageResource: Int,
        )

        fun showFullScreen(
            movieTitle: String,
            totalPrice: String,
        )

        fun showTotalPrice(totalPrice: String)

        fun moveToBookingResultScreen(
            bookingResultUiModel: BookingResultUiModel,
            seatsUiModel: SeatsUiModel,
        )
    }
}

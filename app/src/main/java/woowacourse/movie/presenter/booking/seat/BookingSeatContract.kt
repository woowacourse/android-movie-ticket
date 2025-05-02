package woowacourse.movie.presenter.booking.seat

import androidx.annotation.StringRes
import woowacourse.movie.ui.model.booking.BookingResultUiModel

interface BookingSeatContract {
    interface Presenter {
        fun loadBookingResult(bookingResultUiModelOrNull: BookingResultUiModel?)

        fun loadInfos()

        fun toggleBackGroundColor(seatPosition: String)

        fun updatePrice()
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
    }
}

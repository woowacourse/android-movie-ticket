package woowacourse.movie.presenter.booking.complete

import androidx.annotation.StringRes
import woowacourse.movie.ui.model.booking.BookingResultUiModel
import woowacourse.movie.ui.model.seat.SeatsUiModel

interface BookingCompleteContract {
    interface Presenter {
        fun loadBookingResult(bookingResultUiModelOrNull: BookingResultUiModel?)

        fun loadSeats(seatsUiModel: SeatsUiModel?)
    }

    interface View {
        fun showErrorMessage(
            @StringRes messageResource: Int,
        )

        fun showBookingResult(bookingResultUiModel: BookingResultUiModel)

        fun showSeatsPosition(seatsPosition: String)

        fun showTotalPrice(totalPrice: String)
    }
}

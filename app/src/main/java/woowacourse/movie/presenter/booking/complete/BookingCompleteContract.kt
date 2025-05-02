package woowacourse.movie.presenter.booking.complete

import androidx.annotation.StringRes
import woowacourse.movie.ui.model.booking.BookingResultUiModel

interface BookingCompleteContract {
    interface Presenter {
        fun loadBookingResult(bookingResultUiModelOrNull: BookingResultUiModel?)
    }

    interface View {
        fun showErrorMessage(
            @StringRes messageResource: Int,
        )

        fun showBookingResult(bookingResultUiModel: BookingResultUiModel)

        fun showBookingAmount(totalPrice: String)
    }
}

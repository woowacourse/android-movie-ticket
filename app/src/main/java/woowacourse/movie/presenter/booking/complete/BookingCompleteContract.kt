package woowacourse.movie.presenter.booking.complete

import androidx.annotation.StringRes
import woowacourse.movie.domain.model.booking.TicketPrice
import woowacourse.movie.ui.model.booking.BookingResultUiModel

interface BookingCompleteContract {
    interface Presenter {
        fun loadBookingResult(
            bookingResultUiModel: BookingResultUiModel,
            ticketPrice: TicketPrice,
        )
    }

    interface View {
        fun showErrorMessage(
            @StringRes messageResource: Int,
        )

        fun showBookingResult(bookingResultUiModel: BookingResultUiModel)

        fun showBookingAmount(totalPrice: String)
    }
}

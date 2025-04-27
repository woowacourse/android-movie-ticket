package woowacourse.movie.presenter.booking.complete

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
        fun showErrorMessage(message: String)

        fun showBookingResult(bookingResultUiModel: BookingResultUiModel)

        fun showBookingAmount(totalPrice: String)
    }
}

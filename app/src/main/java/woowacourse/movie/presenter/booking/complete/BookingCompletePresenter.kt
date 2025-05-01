package woowacourse.movie.presenter.booking.complete

import android.icu.text.DecimalFormat
import woowacourse.movie.R
import woowacourse.movie.domain.model.booking.BookingResult
import woowacourse.movie.domain.model.booking.TicketPrice
import woowacourse.movie.ui.model.booking.BookingResultUiModel
import woowacourse.movie.util.mapper.BookingResultModelMapper

class BookingCompletePresenter(
    val view: BookingCompleteContract.View,
    bookingResultUiModel: BookingResultUiModel?,
) : BookingCompleteContract.Presenter {
    private val ticketPrice = TicketPrice()
    private lateinit var bookingResult: BookingResult

    init {
        if (bookingResultUiModel == null) {
            view.showErrorMessage(R.string.error_not_exist_booking_result)
        } else {
            loadBookingResult(bookingResultUiModel, ticketPrice)
        }
    }

    override fun loadBookingResult(
        bookingResultUiModel: BookingResultUiModel,
        ticketPrice: TicketPrice,
    ) {
        bookingResult = BookingResultModelMapper.toDomain(bookingResultUiModel)
        view.showBookingResult(bookingResultUiModel)

        val money = bookingResult.calculateAmount(ticketPrice)
        val bookingAmount = DecimalFormat("#,###").format(money)
        view.showBookingAmount(bookingAmount)
    }
}

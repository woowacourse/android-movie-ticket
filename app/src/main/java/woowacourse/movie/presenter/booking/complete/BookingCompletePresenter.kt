package woowacourse.movie.presenter.booking.complete

import woowacourse.movie.R
import woowacourse.movie.domain.model.booking.BookingResult
import woowacourse.movie.domain.model.booking.TicketPrice
import woowacourse.movie.ui.model.booking.BookingResultUiModel
import woowacourse.movie.util.StringFormatter
import woowacourse.movie.util.mapper.BookingResultModelMapper

class BookingCompletePresenter(
    val view: BookingCompleteContract.View,
) : BookingCompleteContract.Presenter {
    private val ticketPrice = TicketPrice()
    private lateinit var bookingResult: BookingResult

    override fun loadBookingResult(bookingResultUiModelOrNull: BookingResultUiModel?) {
        bookingResultUiModelOrNull?.let { bookingResultUiModel ->
            bookingResult = BookingResultModelMapper.toDomain(bookingResultUiModel)
            view.showBookingResult(bookingResultUiModel)

            val money = bookingResult.calculateAmount(ticketPrice)
            val bookingAmount = StringFormatter.toPriceFormat(money)
            view.showBookingAmount(bookingAmount)
        } ?: view.showErrorMessage(R.string.error_not_exist_booking_result)
    }
}

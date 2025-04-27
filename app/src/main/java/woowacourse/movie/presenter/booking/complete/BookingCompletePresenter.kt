package woowacourse.movie.presenter.booking.complete

import android.icu.text.DecimalFormat
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
            view.showErrorMessage(ERROR_NOT_EXIST_BOOKING_RESULT)
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

    companion object {
        private const val ERROR_NOT_EXIST_BOOKING_RESULT = "영화 예매 정보가 없습니다."
    }
}

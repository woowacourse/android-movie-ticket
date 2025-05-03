package woowacourse.movie.presenter.booking.seat

import woowacourse.movie.R
import woowacourse.movie.domain.model.booking.BookingResult
import woowacourse.movie.domain.model.seat.Seat
import woowacourse.movie.domain.model.seat.Seats
import woowacourse.movie.ui.model.booking.BookingResultUiModel
import woowacourse.movie.util.StringFormatter
import woowacourse.movie.util.mapper.BookingResultModelMapper
import woowacourse.movie.util.mapper.SeatsModelMapper

class BookingSeatPresenter(
    private val view: BookingSeatContract.View,
) : BookingSeatContract.Presenter {
    private lateinit var seats: Seats
    private lateinit var bookingResultUiModel: BookingResultUiModel
    private lateinit var bookingResult: BookingResult

    override fun loadBookingResult(bookingResultUiModelOrNull: BookingResultUiModel?) {
        bookingResultUiModelOrNull?.let {
            bookingResultUiModel = bookingResultUiModelOrNull
            bookingResult = BookingResultModelMapper.toDomain(bookingResultUiModel)
            seats = Seats(bookingResult.headCount)
        } ?: view.showErrorMessage(R.string.error_not_exist_booking_result)
    }

    override fun loadInfos() {
        val money = seats.totalPrice()
        val totalPrice = StringFormatter.toPriceFormat(money)
        view.showFullScreen(bookingResultUiModel.title, totalPrice)
    }

    override fun updateSeat(seatPosition: String) {
        val targetSeat = Seat.of(seatPosition)
        val isReserved = seats.isReservedSeat(targetSeat)

        if (seats.isSeatSelectionComplete()) {
            allSeatSelectionByIsReserved(isReserved, targetSeat, seatPosition)
            return
        }

        if (!isReserved) {
            seats.reserve(targetSeat)
        } else {
            seats.cancelReserve(targetSeat)
        }

        view.showSeatView(seatPosition, !isReserved)
    }

    private fun allSeatSelectionByIsReserved(
        isReserved: Boolean,
        targetSeat: Seat,
        seatPosition: String,
    ) {
        if (isReserved) {
            seats.cancelReserve(targetSeat)
            view.showSeatView(seatPosition, false)
        }
    }

    override fun updatePrice() {
        val money = seats.totalPrice()
        val totalPrice = StringFormatter.toPriceFormat(money)
        view.showTotalPrice(totalPrice)
    }

    override fun proceedToBookingResult() {
        val seatsUiModel = SeatsModelMapper.toUi(seats)
        view.moveToBookingResultScreen(bookingResultUiModel, seatsUiModel)
    }
}

package woowacourse.movie.presenter.booking.seat

import android.icu.text.DecimalFormat
import woowacourse.movie.R
import woowacourse.movie.domain.model.seat.Seat
import woowacourse.movie.domain.model.seat.Seats
import woowacourse.movie.ui.model.booking.BookingResultUiModel

class BookingSeatPresenter(
    private val view: BookingSeatContract.View,
    bookingResultUiOrNull: BookingResultUiModel?,
) : BookingSeatContract.Presenter {
    private val seats = Seats()
    private val reservedSeat get() = seats.reserveSeats
    private lateinit var bookingResultUiModel: BookingResultUiModel

    init {
        if (bookingResultUiOrNull == null) {
            view.showErrorMessage(R.string.error_not_exist_booking_result)
        } else {
            bookingResultUiModel = bookingResultUiOrNull
        }
    }

    override fun loadInfos() {
        val money = seats.totalPrice()
        val totalPrice = DecimalFormat("#,###").format(money)
        view.showFullScreen(bookingResultUiModel.title, totalPrice)
    }

    override fun toggleBackGroundColor(seatPosition: String) {
        val targetSeat = Seat.of(seatPosition)
        val isReserved = seats.isReservedSeat(targetSeat)
        if (isReserved) {
            seats.reserve(targetSeat)
        } else {
            seats.cancelReserve(targetSeat)
        }

        view.showSeatView(seatPosition, isReserved)
    }

    override fun updatePrice() {
        val money = seats.totalPrice()
        val totalPrice = DecimalFormat("#,###").format(money)
        view.showTotalPrice(totalPrice)
    }
}

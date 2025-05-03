package woowacourse.movie.presenter.booking.complete

import woowacourse.movie.R
import woowacourse.movie.ui.model.booking.BookingResultUiModel
import woowacourse.movie.ui.model.seat.SeatsUiModel
import woowacourse.movie.util.mapper.SeatsModelMapper

class BookingCompletePresenter(
    val view: BookingCompleteContract.View,
) : BookingCompleteContract.Presenter {
    override fun loadBookingResult(bookingResultUiModelOrNull: BookingResultUiModel?) {
        bookingResultUiModelOrNull?.let { bookingResultUiModel ->
            view.showBookingResult(bookingResultUiModel)
        } ?: view.showErrorMessage(R.string.error_not_exist_booking_result)
    }

    override fun loadSeats(seatsUiModel: SeatsUiModel?) {
        seatsUiModel?.let {
            view.showSeatsPosition(seatsUiModel.reservingSeatsNames())

            val seats = SeatsModelMapper.toModel(seatsUiModel)
            val totalPrice = seats.totalPrice().toString()
            view.showTotalPrice(totalPrice)
        } ?: view.showErrorMessage(R.string.error_not_exist_seats)
    }
}

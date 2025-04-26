package woowacourse.movie.feature.bookingseat.presenter

import woowacourse.movie.domain.model.BookingInfo
import woowacourse.movie.domain.model.MovieSeat
import woowacourse.movie.domain.model.SeatSelectionResult
import woowacourse.movie.feature.bookingseat.contract.BookingSeatContract
import woowacourse.movie.feature.mapper.toDomain
import woowacourse.movie.feature.mapper.toUi
import woowacourse.movie.feature.model.BookingInfoUiModel
import woowacourse.movie.feature.model.MovieSeatUiModel
import woowacourse.movie.feature.model.SeatSelectionUiState

class BookingSeatPresenter(
    private val view: BookingSeatContract.View,
) : BookingSeatContract.Presenter {
    private lateinit var bookingInfo: BookingInfo

    override fun onCreateView(bookingInfo: BookingInfoUiModel) {
        this.bookingInfo = bookingInfo.toDomain()
        view.showSeats()
        view.showBookingInfo(bookingInfo)
        view.updatePrice(this.bookingInfo.totalPrice.value)
        view.updateSeatSelectionCompleteButton(this.bookingInfo.isSeatAllSelected)
    }

    override fun onSeatSetup(
        row: Int,
        column: Int,
    ): MovieSeatUiModel = MovieSeat(row, column).toUi()

    override fun onSeatClicked(seat: MovieSeatUiModel): SeatSelectionUiState {
        val result = bookingInfo.updateSeat(seat.toDomain())

        view.updateSeatSelectionCompleteButton(bookingInfo.isSeatAllSelected)

        return when (result) {
            is SeatSelectionResult.Success -> {
                view.updatePrice(bookingInfo.totalPrice.value)
                SeatSelectionUiState.Success(result.selectedSeat.toUi())
            }

            is SeatSelectionResult.ExceedCountFailure -> SeatSelectionUiState.ExceedCountFailure
        }
    }

    override fun onSeatSelectionCompleteClicked() {
        view.showBookingCompleteDialog()
    }

    override fun onSeatSelectionCompleteConfirmed() {
        view.navigateToBookingComplete(bookingInfo.toUi())
    }

    override fun onBackButtonClicked() {
        view.navigateToBack()
    }
}

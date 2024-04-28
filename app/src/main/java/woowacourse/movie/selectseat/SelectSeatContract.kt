package woowacourse.movie.selectseat

import woowacourse.movie.moviereservation.uimodel.BookingInfoUiModel
import woowacourse.movie.selectseat.uimodel.PriceUiModel
import woowacourse.movie.selectseat.uimodel.SeatUiModel

interface SelectSeatContract {
    interface View {
        fun showSeat(theaterSeats: List<SeatUiModel>)

        fun showMovieInfo(
            title: String,
            priceUiModel: PriceUiModel,
        )

        fun updatePrice(updatedPrice: PriceUiModel)

        fun navigateToResult(reservationId: Long)
    }

    interface Presenter {
        fun loadSeat(movieId: Long)

        fun loadReservationInfo(movieId: Long)

        fun calculatePrice(selectedSeats: List<SeatUiModel>)

        fun completeReservation(
            bookingInfoUiModel: BookingInfoUiModel,
            selectedSeats: List<SeatUiModel>,
        )
    }
}

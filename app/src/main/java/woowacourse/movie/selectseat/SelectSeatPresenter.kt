package woowacourse.movie.selectseat

import woowacourse.movie.model.ReserveSeats
import woowacourse.movie.moviereservation.toHeadCount
import woowacourse.movie.moviereservation.uimodel.BookingInfoUiModel
import woowacourse.movie.repository.MovieRepository
import woowacourse.movie.selectseat.uimodel.PriceUiModel
import woowacourse.movie.selectseat.uimodel.SeatUiModel

class SelectSeatPresenter(
    private val view: SelectSeatContract.View,
    private val repository: MovieRepository,
) : SelectSeatContract.Presenter {
    override fun loadSeat(movieId: Long) {
        runCatching {
            repository.screenMovieById(movieId)
        }.onSuccess {
            view.showSeat(it.theater.seats().toSeatsUiModel())
        }
    }

    override fun loadReservationInfo(movieId: Long) {
        runCatching {
            repository.screenMovieById(movieId)
        }.onSuccess {
            view.showMovieInfo(it.movie.title, PriceUiModel(it.theater.defaultPrice))
        }
    }

    override fun calculatePrice(selectedSeats: List<SeatUiModel>) {
        val updatedPrice = ReserveSeats(selectedSeats.toSeats()).totalPrice
        view.updatePrice(PriceUiModel(updatedPrice.price.toInt()))
    }

    override fun completeReservation(
        bookingInfoUiModel: BookingInfoUiModel,
        selectedSeats: List<SeatUiModel>,
    ) {
        runCatching {
            repository.reserveMovie(
                bookingInfoUiModel.movieId,
                bookingInfoUiModel.localDateTime(),
                bookingInfoUiModel.count.toHeadCount(),
                ReserveSeats(selectedSeats.toSeats()),
            )
        }.onSuccess {
            view.navigateToResult(it)
        }
    }
}

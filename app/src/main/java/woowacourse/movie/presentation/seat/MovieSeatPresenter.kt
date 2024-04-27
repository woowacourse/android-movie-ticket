package woowacourse.movie.presentation.seat

import woowacourse.movie.data.MovieRepositoryImpl
import woowacourse.movie.domain.MovieRepository
import woowacourse.movie.domain.MovieSeat
import woowacourse.movie.utils.MovieErrorCode

class MovieSeatPresenter(
    private val seatContractView: MovieSeatContract.View,
    private var movieRepository: MovieRepository = MovieRepositoryImpl(),
) : MovieSeatContract.Presenter {
    private var _uiModel: SeatUiModel = SeatUiModel()
    val uiModel: SeatUiModel
        get() = _uiModel

    override fun loadSeats(
        movieId: Long,
        movieScreenDateTimeId: Long,
        countThreshold: Int,
    ) {
        val movie = movieRepository.findMovieById(movieId)
        val seats =
            movieRepository.findSeatsByMovieScreenDateTimeId(
                movieScreenDateTimeId,
            )
        movie?.let {
            _uiModel =
                _uiModel.copy(
                    movieId = movieId,
                    movieTitle = it.title,
                    movieScreenDateTimeId = movieScreenDateTimeId,
                    countThreshold = countThreshold,
                )
            seatContractView.onInitView(it, seats)
        } ?: seatContractView.onError(MovieErrorCode.INVALID_MOVIE_ID)
    }

    override fun selectSeat(
        buttonIndex: Int,
        movieSeat: MovieSeat,
        selectedState: Boolean,
    ) {
        when (selectedState) {
            true -> {
                _uiModel = _uiModel.copy(selectedSeat = _uiModel.selectedSeat - movieSeat)
                seatContractView.onSeatUpdate(buttonIndex, isSelected = false)
            }
            false -> {
                if (_uiModel.selectedCount < _uiModel.countThreshold) {
                    _uiModel = _uiModel.copy(selectedSeat = _uiModel.selectedSeat + movieSeat)
                    seatContractView.onSeatUpdate(buttonIndex, isSelected = true)
                }
            }
        }
        seatContractView.onPriceUpdate(_uiModel.totalPrice)
        seatContractView.onReservationButtonChanged(_uiModel.countThreshold == _uiModel.selectedCount)
    }

    override fun reservation() {
        seatContractView.onReservationComplete(
            _uiModel.movieId,
            _uiModel.movieScreenDateTimeId,
            _uiModel.selectedSeat.map { it.id },
            _uiModel.selectedCount,
            _uiModel.totalPrice,
        )
    }
}

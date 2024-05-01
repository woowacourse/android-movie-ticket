package woowacourse.movie.presentation.seat

import woowacourse.movie.data.MovieRepositoryImpl
import woowacourse.movie.domain.MovieRepository
import woowacourse.movie.domain.MovieSeat
import woowacourse.movie.utils.MovieErrorCode

class MovieSeatPresenter(
    private val seatContractView: MovieSeatContract.View,
    private var movieRepository: MovieRepository = MovieRepositoryImpl(),
) : MovieSeatContract.Presenter {
    private var _seatUiModel: SeatUiModel = SeatUiModel()
    private var movieInfoUiModel: MovieInfoUiModel = MovieInfoUiModel()
    val seatUiModel: SeatUiModel
        get() = _seatUiModel

    override fun loadSeats(
        movieId: Long,
        movieScreenDateTimeId: Long,
        countThreshold: Int,
    ) {
        val movie =
            movieRepository.findMovieById(movieId) ?: run {
                seatContractView.onError(MovieErrorCode.INVALID_MOVIE_ID)
                return
            }
        val seats =
            movieRepository.findSeatsByMovieScreenDateTimeId(
                movieScreenDateTimeId,
            )
        movieInfoUiModel =
            movieInfoUiModel.copy(
                movieId = movieId,
                movieTitle = movie.title,
                movieScreenDateTimeId = movieScreenDateTimeId,
            )
        _seatUiModel =
            _seatUiModel.copy(
                countThreshold = countThreshold,
            )
        seatContractView.onInitView(movie, seats)
    }

    override fun selectSeat(
        buttonIndex: Int,
        movieSeat: MovieSeat,
        selectedState: Boolean,
    ) {
        // 최대 갯수보다 더 선택하면 ui모델에서 copy 결과로 null 반환
        _seatUiModel.select(movieSeat, selectedState)?.let {
            _seatUiModel = it
            seatContractView.onSeatUpdate(buttonIndex, isSelected = !selectedState)
            seatContractView.onPriceUpdate(_seatUiModel.totalPrice)
            seatContractView.onReservationButtonChanged(_seatUiModel.countThreshold == _seatUiModel.selectedCount)
        }
    }

    override fun reservation() {
        seatContractView.onReservationComplete(
            movieInfoUiModel.movieId,
            movieInfoUiModel.movieScreenDateTimeId,
            _seatUiModel.selectedSeat.map { it.id },
        )
    }
}

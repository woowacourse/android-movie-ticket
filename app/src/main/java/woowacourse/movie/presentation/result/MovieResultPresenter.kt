package woowacourse.movie.presentation.result

import woowacourse.movie.data.MovieRepositoryImpl
import woowacourse.movie.domain.MovieRepository
import woowacourse.movie.utils.MovieErrorCode

class MovieResultPresenter(
    private val resultContractView: MovieResultContract.View,
    private var movieRepository: MovieRepository = MovieRepositoryImpl(),
) :
    MovieResultContract.Presenter {
    private var resultUiModel: ResultUiModel = ResultUiModel()

    override fun loadResult(
        movieId: Long,
        movieScreenDateTimeId: Long,
        seatIds: List<Long>,
    ) {
        val movie =
            movieRepository.findMovieById(movieId) ?: run {
                resultContractView.onError(MovieErrorCode.INVALID_MOVIE_ID)
                return
            }
        val screenDateTime =
            movieRepository.findScreenDateTimeByMovieScreenDateTimeId(movieScreenDateTimeId) ?: run {
                resultContractView.onError(MovieErrorCode.INVALID_SCREEN_DATE_TIME_ID)
                return
            }
        val movieSeats =
            seatIds.map {
                movieRepository.findSeatById(it)
                    ?: run {
                        resultContractView.onError(MovieErrorCode.INVALID_SEAT_ID)
                        return
                    }
            }
        resultUiModel =
            resultUiModel.copy(
                movieTitle = movie.title,
                localDateTime = screenDateTime.dateTime,
                seats = movieSeats,
            )
        resultContractView.onUpdateView(resultUiModel)
    }
}

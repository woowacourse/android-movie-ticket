package woowacourse.movie.presentation.result

import woowacourse.movie.data.MovieRepositoryImpl
import woowacourse.movie.domain.MovieRepository
import woowacourse.movie.utils.MovieErrorCode

class MovieResultPresenter(private val resultContractView: MovieResultContract.View) :
    MovieResultContract.Presenter {
    private var movieRepository: MovieRepository = MovieRepositoryImpl()

    private var _uiModel: ResultUiModel = ResultUiModel()
    val uiModel: ResultUiModel
        get() = _uiModel

    override fun display(
        movieId: Long,
        movieScreenDateTimeId: Long,
        seatIds: List<Long>,
        count: Int,
    ) {
        val movie = movieRepository.findMovieById(movieId)
        val screenDateTime =
            movieRepository.findScreenDateTimeByMovieScreenDateTimeId(movieScreenDateTimeId)
        val movieSeats = seatIds.map { movieRepository.findSeatById(it) }

        if (movie != null && screenDateTime != null) {
            _uiModel =
                _uiModel.copy(
                    movieTitle = movie.title,
                    localDateTime = screenDateTime.dateTime,
                    seats = movieSeats,
                )
            resultContractView.onInitView(resultUiModel = _uiModel)
        } else {
            resultContractView.onError(MovieErrorCode.INVALID_MOVIE_ID)
        }
    }
}

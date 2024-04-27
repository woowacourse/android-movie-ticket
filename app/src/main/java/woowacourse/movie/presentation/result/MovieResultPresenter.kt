package woowacourse.movie.presentation.result

import woowacourse.movie.data.MovieRepositoryImpl
import woowacourse.movie.domain.MovieRepository
import woowacourse.movie.utils.MovieErrorCode

class MovieResultPresenter(
    private val resultContractView: MovieResultContract.View,
    private var movieRepository: MovieRepository = MovieRepositoryImpl(),
) :
    MovieResultContract.Presenter {
    private var uiModel: ResultUiModel = ResultUiModel()

    override fun loadResult(
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
            uiModel =
                uiModel.copy(
                    movieTitle = movie.title,
                    localDateTime = screenDateTime.dateTime,
                    seats = movieSeats,
                )
            resultContractView.onUpdateView(resultUiModel = uiModel)
        } else {
            resultContractView.onError(MovieErrorCode.INVALID_MOVIE_ID)
        }
    }
}

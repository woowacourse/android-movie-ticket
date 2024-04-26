package woowacourse.movie.feature.complete

import woowacourse.movie.model.data.MovieRepository

class MovieReservationCompletePresenter(
    private val view: MovieReservationCompleteContract.View,
    private val movieRepository: MovieRepository,
) : MovieReservationCompleteContract.Presenter {
    override fun loadMovieData(movieId: Long) {
        val movie = movieRepository.find(movieId)
        view.setUpReservationCompleteView(movie)
    }
}

package woowacourse.movie.movieList

import woowacourse.movie.uiModel.MovieInfoUIModel

class MovieListPresenter(
    val view: MovieListContract.View,
    private val repository: MovieRepository = SampleMovieRepository(),
) : MovieListContract.Presenter {
    override fun loadMovies() {
        val movies = repository.getMovies()
        if (movies.isEmpty()) {
            view.showError()
        } else {
            view.showMovies(movies)
        }
    }

    override fun onMovieSelected(movie: MovieInfoUIModel) {
        view.navigateToBooking(movie)
    }
}

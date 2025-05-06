package woowacourse.movie.movieList

import woowacourse.movie.uiModel.AdUIModel
import woowacourse.movie.uiModel.MovieInfoUIModel
import woowacourse.movie.uiModel.MovieListItem

class MovieListPresenter(
    private val view: MovieListContract.View,
    private val repository: MovieRepository = SampleMovieRepository(),
) : MovieListContract.Presenter {
    override fun loadMovies() {
        val movies = repository.getMovies()
        if (movies.isEmpty()) {
            view.showError()
            return
        }

        val items = mutableListOf<MovieListItem>()
        val adInterval = 3
        for ((index, movie) in movies.withIndex()) {
            items.add(movie)
            if ((index + 1) % adInterval == 0) {
                items.add(AdUIModel)
            }
        }

        view.showMovies(items)
    }

    override fun onMovieSelected(movie: MovieInfoUIModel) {
        view.navigateToBooking(movie)
    }
}

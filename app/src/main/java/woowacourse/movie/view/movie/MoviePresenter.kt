package woowacourse.movie.view.movie

import woowacourse.movie.model.Movie

class MoviePresenter(
    val view: MovieContract.View,
) : MovieContract.Presenter {
    override fun fetchMovies() {
        val movies = Movie.values
        view.updateView(movies)
    }
}

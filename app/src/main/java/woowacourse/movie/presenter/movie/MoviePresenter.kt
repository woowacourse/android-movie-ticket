package woowacourse.movie.presenter.movie

import woowacourse.movie.model.Movie
import woowacourse.movie.view.movie.MovieContract

class MoviePresenter(
    val view: MovieContract.View,
) : MovieContract.Presenter {
    override fun fetchMovies() {
        val movies = Movie.values
        view.updateView(movies)
    }
}

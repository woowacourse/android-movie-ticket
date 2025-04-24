package woowacourse.movie.view.movie

import woowacourse.movie.common.DummyData

class MovieListPresenter(
    private val view: MovieListContract.View,
) : MovieListContract.Presenter {
    override fun loadMovieListScreen() {
        val movies = DummyData.movies
        view.showMovieList(movies)
    }
}

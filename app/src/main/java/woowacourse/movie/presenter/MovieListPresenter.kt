package woowacourse.movie.presenter

import woowacourse.movie.contract.MovieListContract
import woowacourse.movie.model.MovieDataSource

class MovieListPresenter(
    val view: MovieListContract.View,
) : MovieListContract.Presenter {
    override val movieList = MovieDataSource.movieList

    override fun setMoviesInfo() {
        view.showMoviesInfo(movieList)
    }

    override fun setListViewClickListenerInfo() {
        view.setOnListViewClickListener(movieList)
    }
}

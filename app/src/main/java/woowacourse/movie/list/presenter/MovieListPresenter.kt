package woowacourse.movie.list.presenter

import woowacourse.movie.list.contract.MovieListContract
import woowacourse.movie.list.model.MovieDataSource

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

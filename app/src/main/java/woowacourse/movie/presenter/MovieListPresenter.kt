package woowacourse.movie.presenter

import woowacourse.movie.contract.MovieListContract
import woowacourse.movie.model.MovieData

class MovieListPresenter(
    val view: MovieListContract.View,
) : MovieListContract.Presenter {
    override val movieList = MovieData.movieList

    override fun setMoviesInfo() {
        view.showMoviesInfo(movieList)
    }

    override fun setListViewClickListenerInfo() {
        view.setOnListViewClickListener(movieList)
    }
}

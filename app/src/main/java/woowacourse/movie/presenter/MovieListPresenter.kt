package woowacourse.movie.presenter

import woowacourse.movie.domain.MovieListContract
import woowacourse.movie.domain.model.MovieData

class MovieListPresenter(
    val view: MovieListContract.View,
) : MovieListContract.Presenter {
    override val movieList = MovieData.movieList

    override fun setListViewInfo() {
        view.showMovieInfo(movieList)
    }

    override fun setListViewClickListenerInfo() {
        view.setOnListViewClickListener(movieList)
    }
}

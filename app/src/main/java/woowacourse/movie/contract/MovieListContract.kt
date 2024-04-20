package woowacourse.movie.contract

import woowacourse.movie.model.Movie
import woowacourse.movie.presenter.MovieListPresenter

interface MovieListContract {
    interface View {
        fun showMoviesInfo(info: ArrayList<Movie>)

        fun setOnListViewClickListener(info: ArrayList<Movie>)

        val presenter: MovieListPresenter
    }

    interface Presenter {
        val movieList: ArrayList<Movie>

        fun setMoviesInfo()

        fun setListViewClickListenerInfo()
    }
}

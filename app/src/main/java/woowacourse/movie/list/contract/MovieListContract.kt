package woowacourse.movie.list.contract

import woowacourse.movie.list.model.Movie
import woowacourse.movie.list.presenter.MovieListPresenter

interface MovieListContract {
    interface View {
        val presenter: MovieListPresenter

        fun showMoviesInfo(info: ArrayList<Movie>)

        fun setOnListViewClickListener(info: ArrayList<Movie>)
    }

    interface Presenter {
        val movieList: ArrayList<Movie>

        fun setMoviesInfo()

        fun setListViewClickListenerInfo()
    }
}

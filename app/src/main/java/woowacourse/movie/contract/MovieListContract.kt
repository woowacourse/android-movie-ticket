package woowacourse.movie.contract

import woowacourse.movie.model.Movie
import woowacourse.movie.presenter.MovieListPresenter

interface MovieListContract {
    interface View {
        fun showMovieInfo(info: ArrayList<Movie>)

        fun setOnListViewClickListener(info: ArrayList<Movie>)

        val presenter: MovieListPresenter
    }

    interface Presenter {
        val movieList: ArrayList<Movie>

        fun setListViewInfo()

        fun setListViewClickListenerInfo()
    }
}

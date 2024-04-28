package woowacourse.movie.list.contract

import woowacourse.movie.list.model.Movie

interface MovieListContract {
    interface View {
        val presenter: Presenter

        fun showMoviesInfo(movies: List<Movie>)

        fun setOnListViewClickListener()
    }

    interface Presenter {
        val movieList: List<Movie>

        fun setMoviesInfo()

        fun setListViewClickListenerInfo()
    }
}

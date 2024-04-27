package woowacourse.movie.list.contract

import woowacourse.movie.list.model.Movie

interface MovieListContract {
    interface View {
        val presenter: Presenter

        fun showMoviesInfo(info: ArrayList<Movie>)

        fun setOnListViewClickListener()
    }

    interface Presenter {
        val movieList: ArrayList<Movie>

        fun setMoviesInfo()

        fun setListViewClickListenerInfo()
    }
}

package woowacourse.movie.list.contract

import woowacourse.movie.list.model.Advertisement
import woowacourse.movie.list.model.Movie

interface MovieListContract {
    interface View {
        val presenter: Presenter

        fun showMoviesInfo(
            movies: List<Movie>,
            advertisements: List<Advertisement>,
        )

        fun setOnListViewClickListener()
    }

    interface Presenter {
        val movieList: List<Movie>

        fun setMoviesInfo()

        fun setListViewClickListenerInfo()
    }
}

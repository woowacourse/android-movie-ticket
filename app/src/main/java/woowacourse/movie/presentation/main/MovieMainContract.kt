package woowacourse.movie.presentation.main

import woowacourse.movie.presentation.adapter.viewtype.MovieItemViewType

interface MovieMainContract {
    interface View {
        fun onMovieItemClick(id: Long)

        fun onInitView(movieItems: List<MovieItemViewType>)
    }

    interface Presenter {
        fun loadMovies()
    }
}

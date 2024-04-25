package woowacourse.movie.presentation.main

import woowacourse.movie.presentation.adapter.MovieAdapter

interface MovieMainContract {
    interface View {
        fun onMovieItemClick(id: Long)

        fun onInitAdapter(adapter: MovieAdapter)
    }

    interface Presenter {
        fun loadMovies()
    }
}

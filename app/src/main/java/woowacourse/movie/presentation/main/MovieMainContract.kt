package woowacourse.movie.presentation.main

import woowacourse.movie.domain.Movie

interface MovieMainContract {
    interface View {
        fun onMovieItemClick(id: Long)

        fun onInitView(movies: List<Movie>)
    }

    interface Presenter {
        fun loadMovies()
    }
}

package woowacourse.movie.main.presenter

import woowacourse.movie.model.MovieAdapter

interface MovieMainContract {
    interface View {
        fun onMovieItemClick(id: Long)

        fun onInitAdapter(adapter: MovieAdapter)
    }

    interface Presenter {
        fun loadMovies()
    }
}

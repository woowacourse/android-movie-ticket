package woowacourse.movie.view.movies

import woowacourse.movie.domain.model.Movie

interface MoviesContract {
    interface View {
        fun showMovies(movies: List<Movie>)
    }

    interface Presenter {
        fun loadData()
    }
}

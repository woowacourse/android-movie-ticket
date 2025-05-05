package woowacourse.movie.ui.movies

import woowacourse.movie.domain.movies.MovieListItem

interface MovieContract {
    interface View {
        fun showAllMovies(movieListItems: List<MovieListItem>)
    }

    interface Presenter {
        fun loadAllMovies()
    }
}

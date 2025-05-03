package woowacourse.movie.view.movie

import woowacourse.movie.view.movie.adapter.MovieListItem

interface MovieListContract {
    interface View {
        fun showMovieList(movies: List<MovieListItem>)
    }

    interface Presenter {
        fun loadMovieListScreen()
    }
}

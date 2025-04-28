package woowacourse.movie.movielist

import woowacourse.movie.domain.Movie

interface MovieListContract {
    interface Presenter {
        fun updateMovies()
    }

    interface View {
        fun showMovieList(movies: List<ItemType>)

        fun clickedButton(movie: Movie)
    }
}

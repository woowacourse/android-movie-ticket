package woowacourse.movie.activity.movielist

import woowacourse.movie.domain.Movie

interface MovieListContract {
    interface View {
        fun showMovieList(movies: List<Movie>)

        fun moveToBooking(movie: Movie)
    }

    interface Presenter {
        fun loadMovies()

        fun onMovieClicked(movie: Movie)
    }
}

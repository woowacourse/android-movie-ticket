package woowacourse.movie.home

import woowacourse.movie.model.Movie

interface HomeContract {
    interface View {
        fun showMovies(movies: List<Movie>)

        fun moveToReservationDetail(movieId: Int)
    }

    interface Presenter {
        fun loadMovies()

        fun deliverMovie(movieId: Int)
    }
}

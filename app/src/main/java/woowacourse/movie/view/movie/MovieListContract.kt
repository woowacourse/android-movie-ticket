package woowacourse.movie.view.movie

import woowacourse.movie.domain.model.Movie

interface MovieListContract {
    interface Presenter {
        fun updateMovieList()

        fun getMovieList(): List<Movie>
    }

    interface View {
        fun setMoveList(movies: List<Movie>)

        fun moveToBookingActivity(movie: Movie)
    }
}

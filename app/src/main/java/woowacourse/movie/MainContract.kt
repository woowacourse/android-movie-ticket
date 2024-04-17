package woowacourse.movie

import woowacourse.movie.model.Movie

interface MainContract {
    interface View {
        fun showMovieList()

        fun moveToMovieDetail(movie: Movie)
    }

    interface Presenter {
        fun createMovieList(): List<Movie>

        fun onReserveButtonClicked(movie: Movie)
    }
}

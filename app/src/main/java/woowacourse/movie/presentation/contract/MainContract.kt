package woowacourse.movie.presentation.contract

import woowacourse.movie.domain.model.Movie

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

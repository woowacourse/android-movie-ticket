package woowacourse.movie.presentation.contract

import woowacourse.movie.domain.model.Movie

interface MainContract {
    interface Model {
        fun initMovieList()
    }

    interface View {
        fun showMovieList()

        fun moveToMovieDetail(movie: Movie)
    }

    interface Presenter {
        fun movieList(): List<Movie>

        fun onReserveButtonClicked(movie: Movie)
    }
}

package woowacourse.movie.presentation.contract

import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.Movies

interface MainContract {
    interface View {
        fun showMovieList()

        fun moveToMovieDetail(movie: Movie)
    }

    interface Presenter {
        val movies: Movies

        fun onReserveButtonClicked(movie: Movie)
    }

    interface ViewActions {
        fun reserveMovie(movie: Movie)
    }
}

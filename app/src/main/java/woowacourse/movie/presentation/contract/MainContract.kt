package woowacourse.movie.presentation.contract

import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.Movies

interface MainContract {
    interface View {
        fun onUpdateMovies(movies: List<Movie>)

        fun showMovieList()

        fun moveToMovieDetail(movie: Movie)
    }

    interface Presenter {
        fun attachView(view: View)

        fun detachView()

        fun onViewSetUp()

        fun onReserveButtonClicked(movie: Movie)
    }

    interface ViewActions {
        fun reserveMovie(movie: Movie)
    }
}

package woowacourse.movie.presentation.contract

import woowacourse.movie.presentation.uimodel.MovieUiModel

interface MainContract {
    interface View {
        fun onUpdateMovies(movies: List<MovieUiModel>)

        fun showMovieList()

        fun moveToMovieDetail(movieId: Int)
    }

    interface Presenter {
        fun attachView(view: View)

        fun detachView()

        fun onViewSetUp()

        fun onReserveButtonClicked(movieId: Int)
    }

    interface ViewActions {
        fun reserveMovie(movieId: Int)
    }
}

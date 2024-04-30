package woowacourse.movie.contract

import woowacourse.movie.uiModels.movie.MovieBrief

interface MovieListContract {
    interface View {
        fun displayMovieBriefs(movieBriefs: List<MovieBrief>)

        fun navigateToMovieDetail(movieId: Int)
    }

    interface Presenter {
        fun loadMovies()

        fun selectMovie(movieId: Int)
    }
}

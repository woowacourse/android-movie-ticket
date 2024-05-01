package woowacourse.movie.presentation.movie_list

import woowacourse.movie.uimodel.movie.MovieBrief

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

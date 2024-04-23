package woowacourse.movie.presentation.screen

import woowacourse.movie.model.Movie

interface ScreeningMovieContract {
    interface View {
        fun startNextActivity(movieId: Int)
    }

    interface Presenter {
        fun registerMovie(movie: Movie)
    }
}

package woowacourse.movie.presenter

import woowacourse.movie.model.Movie

interface MovieMainContract {
    interface View {
        fun onMovieItemClick(movie: Movie)
    }

    interface Presenter
}

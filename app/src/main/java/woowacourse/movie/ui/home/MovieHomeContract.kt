package woowacourse.movie.ui.home

import woowacourse.movie.model.movie.MovieContent

interface MovieHomeContract {
    interface View {
        fun setMovieContentsUi(movieContents: List<MovieContent>)
    }

    interface Presenter {
        fun setMovieContents()
    }
}

package woowacourse.movie.ui.home

import woowacourse.movie.model.movie.MovieContent

interface MovieHomeContract {
    interface View {
        fun showMovieContentsUi(movieContents: List<MovieContent>)
    }

    interface Presenter {
        fun loadMovieContents()
    }
}

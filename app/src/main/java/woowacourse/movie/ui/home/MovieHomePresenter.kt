package woowacourse.movie.ui.home

import woowacourse.movie.model.data.MovieContents

class MovieHomePresenter(
    private val view: MovieHomeContract.View,
    private val movieContents: MovieContents,
) : MovieHomeContract.Presenter {
    override fun loadMovieContents() {
        view.showMovieContents(movieContents.findAll())
    }
}

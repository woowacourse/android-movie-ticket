package woowacourse.movie.feature.home

import woowacourse.movie.model.data.MovieContents

class MovieHomePresenter(
    private val view: MovieHomeContract.View,
    private val movieContents: MovieContents,
) : MovieHomeContract.Presenter {
    override fun loadMovieData() {
        val movieContents = movieContents.findAll()
        view.setUpMovieContentList(movieContents)
    }
}

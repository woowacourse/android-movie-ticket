package woowacourse.movie.feature.home

import woowacourse.movie.model.data.MovieRepository

class MovieHomePresenter(
    private val view: MovieHomeContract.View,
    private val movieRepository: MovieRepository,
) : MovieHomeContract.Presenter {
    override fun loadMovieData() {
        val movies = movieRepository.findAll()
        view.initializeMovieList(movies)
    }
}

package woowacourse.movie.activity.main

import woowacourse.movie.dto.MovieDto
import woowacourse.movie.global.ServiceLocator

class MainPresenter(private val mainView: MainContract.View) : MainContract.Presenter {
    override fun initMovieDto() {
        val movies = ServiceLocator.movies.map { MovieDto.fromMovie(it) }
        mainView.initMovieDto(movies)
    }
}

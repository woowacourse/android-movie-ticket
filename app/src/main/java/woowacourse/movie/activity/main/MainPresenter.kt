package woowacourse.movie.activity.main

import woowacourse.movie.dto.MovieListData
import woowacourse.movie.global.ServiceLocator

class MainPresenter(private val mainView: MainContract.View) : MainContract.Presenter {
    override fun initMovieDto() {
        val movies = ServiceLocator.movies.map { MovieListData.MovieDto.fromMovie(it) }
        val ads = ServiceLocator.ads
        val flatten = MovieListData.flatten(movies, ads)
        mainView.initMovieDto(flatten)
    }
}

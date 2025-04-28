package woowacourse.movie.activity.main

import woowacourse.movie.domain.MovieListData
import woowacourse.movie.dto.MovieListDataDto
import woowacourse.movie.global.ServiceLocator

class MainPresenter(private val mainView: MainContract.View) : MainContract.Presenter {
    override fun initMovieDto() {
        val movies = ServiceLocator.movies
        val ads = ServiceLocator.ads
        val flatten = MovieListData.flatten(movies, ads)
        mainView.initMovieDto(MovieListDataDto.fromDomain(flatten))
    }
}

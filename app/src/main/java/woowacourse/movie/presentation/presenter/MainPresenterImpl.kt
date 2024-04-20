package woowacourse.movie.presentation.presenter

import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.Movies
import woowacourse.movie.presentation.contract.MainContract

class MainPresenterImpl(private val view: MainContract.View) : MainContract.Presenter {
    private val movies = Movies()

    override fun loadMovieList() {
        runCatching { movies.initMovieList() }
            .onSuccess {
                view.showMovieList(movies.movies)
            }
            .onFailure {
                view.showMessage(it.message ?: "영화 목록을 불러오는데 실패했습니다.")
            }
    }

    override fun requestMovieDetail(movie: Movie) {
        view.moveToMovieDetail(movie)
    }
}

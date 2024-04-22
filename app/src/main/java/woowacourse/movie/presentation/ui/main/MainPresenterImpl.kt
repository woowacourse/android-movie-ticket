package woowacourse.movie.presentation.ui.main

import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.Movies

class MainPresenterImpl(private val view: MainContract.View) : MainContract.Presenter {
    private var movies: Movies? = null

    init {
        loadMovies()
    }

    private fun loadMovies() {
        runCatching {
            Movies()
        }.onSuccess {
            movies = it
            view.showMovieList(it.movies)
        }.onFailure {
            view.showMessage("영화 목록을 불러오는데 실패했습니다: ${it.message}")
        }
    }

    override fun loadMovieList() {
        movies?.let {
            view.showMovieList(it.movies)
        } ?: view.showMessage("영화 목록을 불러올 수 없습니다.")
    }

    override fun requestMovieDetail(movie: Movie) {
        view.moveToMovieDetail(movie)
    }
}

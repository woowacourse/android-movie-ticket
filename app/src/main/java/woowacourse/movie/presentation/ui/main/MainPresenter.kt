package woowacourse.movie.presentation.ui.main

import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.presentation.uimodel.MovieUiModel

class MainPresenter(
    private val view: MainContract.View,
    private val movieRepository: MovieRepository,
) : MainContract.Presenter {
    private var movies: List<MovieUiModel>? = null

    init {
        loadMovies()
    }

    override fun loadMovies() {
        runCatching {
            movieRepository.getAllMovies()
        }.onSuccess { movieList ->
            movies = movieList.map(MovieUiModel::fromMovie)
            movies?.let { view.showMovieList(it) } ?: view.showMessage("영화 목록이 없습니다.")
        }.onFailure {
            view.showMessage("영화 목록을 불러오는데 실패했습니다: ${it.message}")
        }
    }

    override fun requestMovieDetail(movieId: Int) {
        movies?.find { it.movieId == movieId }?.let {
            view.moveToMovieDetail(it.movieId)
        } ?: view.showMessage("선택한 영화 정보를 찾을 수 없습니다.")
    }
}

package woowacourse.movie.presentation.ui.main

import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.presentation.dto.MovieViewModel

class MainPresenterImpl(private val view: MainContract.View, private val movieRepository: MovieRepository) : MainContract.Presenter {
    private var movies: List<MovieViewModel>? = null

    init {
        loadMovies()
    }

    private fun loadMovies() {
        runCatching {
            movieRepository.getAllMovies()
        }.onSuccess { movieList ->
            movies = movieList.map { MovieViewModel.fromMovie(it) }
            view.showMovieList(movies!!)
        }.onFailure {
            view.showMessage("영화 목록을 불러오는데 실패했습니다: ${it.message}")
        }
    }

    override fun loadMovieList() {
        movies?.let {
            view.showMovieList(it)
        } ?: view.showMessage("영화 목록을 불러올 수 없습니다.")
    }

    override fun requestMovieDetail(movieId: Int) {
        movies?.find { it.movieId == movieId }?.let {
            view.moveToMovieDetail(it.movieId)
        }
    }
}

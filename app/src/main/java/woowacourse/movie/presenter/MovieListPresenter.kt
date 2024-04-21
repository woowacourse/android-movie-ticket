package woowacourse.movie.presenter

import woowacourse.movie.adapter.MovieAdapter
import woowacourse.movie.contract.MovieListContract
import woowacourse.movie.repository.PseudoTheaterRepository
import woowacourse.movie.repository.TheaterRepository

class MovieListPresenter(
    private val movieListView: MovieListContract.View,
    theaterRepository: TheaterRepository = PseudoTheaterRepository(),
    val movieAdapter: MovieAdapter,
) : MovieListContract.Presenter {
    private val theaters = theaterRepository.getTheaters()

    init {
        movieAdapter.onClick = ::selectMovie
        loadTheaters()
    }

    override fun loadTheaters() {
        movieAdapter.setTheaters(theaters)
    }

    private fun selectMovie(position: Int) {
        movieListView.navigateToMovieDetail(theaters[position])
    }
}

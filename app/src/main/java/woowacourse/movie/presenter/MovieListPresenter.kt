package woowacourse.movie.presenter

import woowacourse.movie.adapter.MovieAdapter
import woowacourse.movie.contract.MovieListContract
import woowacourse.movie.repository.PseudoTheaterRepository
import woowacourse.movie.repository.TheaterRepository

class MovieListPresenter(
    private val movieListView: MovieListContract.View,
    private val theaterRepository: TheaterRepository = PseudoTheaterRepository(),
    val movieAdapter: MovieAdapter,
) : MovieListContract.Presenter {
    private val theaters = theaterRepository.getTheaters()

    init {
        movieAdapter.onClick = ::onItemButtonClicked
        loadTheaters()
    }

    override fun loadTheaters() {
        movieAdapter.setTheaters(theaterRepository.getTheaters())
    }

    private fun onItemButtonClicked(position: Int) {
        movieListView.navigateToMovieDetail(theaters[position])
    }
}

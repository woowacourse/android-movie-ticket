package woowacourse.movie.presenter

import woowacourse.movie.adapter.MovieAdapter
import woowacourse.movie.contract.MovieListContract
import woowacourse.movie.repository.PseudoTheaterRepository
import woowacourse.movie.repository.TheaterRepository

class MovieListActivityPresenter(
    private val movieListView: MovieListContract.View,
    theaterRepository: TheaterRepository = PseudoTheaterRepository(),
    movieAdapter: MovieAdapter,
) : MovieListContract.Presenter {
    private val theaters = theaterRepository.getTheaters()

    init {
        movieAdapter.onClick = ::onItemButtonClicked
        movieAdapter.setTheaters(theaterRepository.getTheaters())
    }

    override fun onItemButtonClicked(position: Int) {
        movieListView.navigateToMovieDetail(theaters[position])
    }
}

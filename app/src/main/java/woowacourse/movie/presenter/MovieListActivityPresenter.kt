package woowacourse.movie.presenter

import android.content.Context
import woowacourse.movie.adapter.MovieAdapter
import woowacourse.movie.contract.MovieListContract
import woowacourse.movie.model.movieInfo.MovieDate
import woowacourse.movie.model.movieInfo.MovieInfo
import woowacourse.movie.model.movieInfo.RunningTime
import woowacourse.movie.model.movieInfo.Synopsis
import woowacourse.movie.model.movieInfo.Title
import woowacourse.movie.model.theater.Theater
import woowacourse.movie.repository.PseudoTheaterRepository
import woowacourse.movie.repository.TheaterRepository
import java.time.LocalDate

class MovieListActivityPresenter(
    private val movieListView: MovieListContract.View,
    theaterRepository: TheaterRepository = PseudoTheaterRepository(),
    movieAdapter: MovieAdapter
): MovieListContract.Presenter {
    private val theaters = theaterRepository.getTheaters()
    init {
        movieAdapter.onClick = ::onItemButtonClicked
        movieAdapter.setTheaters(theaterRepository.getTheaters())
    }

    override fun onItemButtonClicked(position: Int) {
        movieListView.navigateToMovieDetail(theaters[position])
    }
}
package woowacourse.movie.detail.presenter

import woowacourse.movie.detail.presenter.contract.MovieDetailContract
import woowacourse.movie.model.MovieCount
import woowacourse.movie.model.MovieDate.Companion.isWeekend
import woowacourse.movie.model.MovieRepository
import woowacourse.movie.model.MovieTime
import java.time.LocalDate

class MovieDetailPresenter(
    private val movieDetailContractView: MovieDetailContract.View,
    count: Int?,
) : MovieDetailContract.Presenter {
    private var movieRepository: MovieRepository = MovieRepository()
    private var movieCount =
        count?.let { MovieCount(count) } ?: MovieCount()

    override fun loadMovieDetail(id: Long) {
        val movieData = movieRepository.getMovieById(id)
        movieData?.let { movie ->
            movieDetailContractView.displayMovieDetail(movie, movieCount)
            movieDetailContractView.setUpDateSpinner(movie.date)
        }
    }

    override fun loadMovieTime(localDate: LocalDate) {
        val movieTime = MovieTime(isWeekend(localDate))
        movieDetailContractView.setUpTimeSpinner(movieTime)
    }

    override fun plusReservationCount() {
        movieCount = movieCount.inc()
        movieDetailContractView.updateCountView(movieCount.count)
    }

    override fun minusReservationCount() {
        movieCount = movieCount.dec()
        movieDetailContractView.updateCountView(movieCount.count)
    }

    override fun reserveMovie(
        id: Long,
        date: String,
        time: String,
    ) {
        movieDetailContractView.navigateToResultView(
            id,
            date,
            time,
            movieCount.count,
        )
    }
}

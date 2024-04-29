package woowacourse.movie.detail.presenter

import woowacourse.movie.data.MovieRepository.getMovieById
import woowacourse.movie.detail.presenter.contract.MovieDetailContract
import woowacourse.movie.model.MovieCount
import woowacourse.movie.model.MovieDate.Companion.isWeekend
import woowacourse.movie.model.MovieTime
import java.time.LocalDate

class MovieDetailPresenter(
    private val movieDetailContractView: MovieDetailContract.View,
) : MovieDetailContract.Presenter {
    private var movieCount: MovieCount = MovieCount()
    private var timeSpinnerPosition: Int = 0

    override fun loadMovieDetail(id: Long) {
        val movieData = getMovieById(id)
        movieData?.let { movie ->
            movieDetailContractView.displayMovieDetail(movie, movieCount)
            movieDetailContractView.setUpDateSpinner(movie.date)
        }
    }

    override fun loadTimeSpinnerItem(localDate: LocalDate) {
        val movieTime = MovieTime(isWeekend(localDate))
        movieDetailContractView.setUpTimeSpinner(movieTime, timeSpinnerPosition)
    }

    override fun updateTimeSpinnerPosition(position: Int) {
        timeSpinnerPosition = position
    }

    override fun updateRevervationCount(count: Int) {
        movieCount = movieCount.update(count)
        movieDetailContractView.updateCountView(movieCount.count)
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
        movieDetailContractView.navigateToSeatSelectionView(
            id,
            date,
            time,
            movieCount.count,
        )
    }
}

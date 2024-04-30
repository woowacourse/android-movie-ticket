package woowacourse.movie.detail.presenter.contract

import woowacourse.movie.model.Movie
import woowacourse.movie.model.MovieCount
import woowacourse.movie.model.MovieDate
import woowacourse.movie.model.MovieTime
import java.time.LocalDate

interface MovieDetailContract {
    interface View {
        fun updateCountView(count: Int)

        fun setUpDateSpinner(movieDate: MovieDate)

        fun setUpTimeSpinner(
            movieTime: MovieTime,
            position: Int,
        )

        fun displayMovieDetail(
            movieData: Movie?,
            movieCount: MovieCount,
        )

        fun navigateToSeatSelectionView(
            id: Long,
            date: String,
            time: String,
            count: Int,
        )
    }

    interface Presenter {
        fun loadMovieDetail(id: Long)

        fun loadTimeSpinnerItem(localDate: LocalDate)

        fun plusReservationCount()

        fun minusReservationCount()

        fun reserveMovie(
            id: Long,
            date: String,
            time: String,
        )

        fun updateRevervationCount(count: Int)

        fun updateTimeSpinnerPosition(position: Int)
    }
}

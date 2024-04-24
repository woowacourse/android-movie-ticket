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

        fun setUpTimeSpinner(movieTime: MovieTime)

        fun displayMovieDetail(
            movieData: Movie?,
            movieCount: MovieCount,
        )

        fun navigateToResultView(
            id: Long,
            date: String,
            time: String,
            count: Int,
        )
    }

    interface Presenter {
        fun loadMovieDetail(id: Long)

        fun loadMovieTime(localDate: LocalDate)

        fun plusReservationCount()

        fun minusReservationCount()

        fun reserveMovie(
            id: Long,
            date: String,
            time: String,
        )
    }
}

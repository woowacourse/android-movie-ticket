package woowacourse.movie.detail.presenter.contract

import woowacourse.movie.detail.model.MovieReservationCount
import woowacourse.movie.main.model.Movie

interface MovieDetailContract {
    interface View {
        fun updateCount(count: Int)

        fun displayMovieDetail(
            movieData: Movie?,
            movieReservationCount: MovieReservationCount,
        )

        fun navigateToResultView(
            id: Long,
            count: Int,
        )
    }

    interface Presenter {
        fun loadMovieDetail(id: Long)

        fun plusReservationCount()

        fun minusReservationCount()

        fun reserveMovie(id: Long)
    }
}

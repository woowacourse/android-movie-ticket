package woowacourse.movie.presenter

import woowacourse.movie.model.Movie
import woowacourse.movie.model.MovieReservationCount

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

package woowacourse.movie.presenter

import woowacourse.movie.model.Movie
import woowacourse.movie.model.MovieReservationCount

interface MovieDetailContract {
    interface View {
        fun onCountUpdate(count: Int)

        fun onInitView(
            movieData: Movie?,
            movieReservationCount: MovieReservationCount,
        )

        fun onReservationComplete(
            id: Long,
            count: Int,
        )
    }

    interface Presenter {
        fun display(id: Long)

        fun plusReservationCount()

        fun minusReservationCount()

        fun reservation(id: Long)
    }
}

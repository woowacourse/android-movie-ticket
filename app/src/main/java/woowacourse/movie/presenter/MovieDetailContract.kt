package woowacourse.movie.presenter

import woowacourse.movie.model.Movie
import woowacourse.movie.utils.MovieErrorCode

interface MovieDetailContract {
    interface View {
        fun onCountUpdate(count: Int)

        fun onInitView(movie: Movie)

        fun onError(errorCode: MovieErrorCode)

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

package woowacourse.movie.presenter

import woowacourse.movie.model.Movie
import woowacourse.movie.model.ReservationCount

interface MovieDetailContract {
    interface View {
        fun onCountUpdate(count: Int)

        fun onInitView(
            movieData: Movie?,
            reservationCount: ReservationCount,
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

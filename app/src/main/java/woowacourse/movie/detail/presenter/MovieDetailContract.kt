package woowacourse.movie.detail.presenter

import woowacourse.movie.model.Movie
import woowacourse.movie.presenter.BaseContract

interface MovieDetailContract {
    interface View : BaseContract.View {
        fun onCountUpdate(count: Int)

        fun onInitView(movie: Movie)

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

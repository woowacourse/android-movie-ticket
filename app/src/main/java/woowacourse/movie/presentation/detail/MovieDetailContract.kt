package woowacourse.movie.presentation.detail

import woowacourse.movie.domain.Movie
import woowacourse.movie.presentation.base.BaseContract

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

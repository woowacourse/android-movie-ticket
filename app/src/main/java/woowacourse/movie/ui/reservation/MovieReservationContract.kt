package woowacourse.movie.ui.reservation

import woowacourse.movie.model.movie.MovieContent
import woowacourse.movie.model.movie.ReservationCount
import woowacourse.movie.ui.base.BaseView

interface MovieReservationContract {
    interface View : BaseView {
        fun showMovieContentUi(movieContent: MovieContent)

        fun updateReservationCountUi(reservationCount: Int)

        fun moveMovieReservationCompleteView(reservationCount: Int)
    }

    interface Presenter {
        fun loadMovieContent(movieContentId: Long)

        fun updateReservationCount(count: Int = ReservationCount.DEFAULT_VALUE)

        fun decreaseCount()

        fun increaseCount()

        fun reserveMovie()
    }
}
